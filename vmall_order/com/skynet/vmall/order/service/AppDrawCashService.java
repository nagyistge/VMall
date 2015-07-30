package com.skynet.vmall.order.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.RFlow;
import com.skynet.app.flow.service.AppFlowService;
import com.skynet.app.flow.service.BFlowService;
import com.skynet.app.flow.service.RFlowService;
import com.skynet.framework.common.generator.SNGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.GeneratorService;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;
import com.skynet.vmall.base.service.DrawCashService;
import com.skynet.vmall.member.service.AppMemberService;

@InjectName("appdrawcashService")
@IocBean(args = { "refer:dao" }) 
public class AppDrawCashService extends SkynetDaoService
{
	
	public AppDrawCashService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppDrawCashService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	public final static String state_apply = "申请";
	
	@Inject
	GeneratorService generatorService;
	
	@Inject
	BFlowService bflowService;

	@Inject
	RFlowService rflowService;	

	@Inject
	AppFlowService appflowService;	
	
	@Inject
	DrawCashService drawcashService;
	
	// 浏览提现
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 1);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_drawcash ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	public Map checksignature(String decode, String ip, String userwxopenid)
	{
		Map remap = new DynamicObject();
		String state = AuthorService.checksignature(decode, ip, userwxopenid);
		remap.put("state", state);
		if(state.equals("error"))
		{
			remap.put("message", "你的签名验证未通过，无法执行操作请求。");
			return remap;
		}
		if(state.equals("timeout"))
		{
			remap.put("message", "亲，你的系统签名已超时，请退回或刷新后重新再试。");
			return remap;
		}
		
		return remap;
	}
	
	public Map apply(DynamicObject login_token) throws Exception
	{
		Map remap = new DynamicObject();

		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		
		// 检查会员信息是否完整，不完整不允许提交
		AppMemberService memberService = new AppMemberService();
		memberService.setDao(sdao());
		remap = memberService.checkinfo(sdao().fetch(Member.class, userid), login_token);
		if(!("success".equals(remap.get("state"))))
		{
			return remap;
		}
		
		remap.clear();
		
		Member member = sdao().fetch(Member.class, userid);
		
		// 检查是否有未提现的订单商品，如果没有可提现的订单商品，不生成提现申请单。
		int nums = sdao().count(OrderGoodsRebate.class, Cnd.where("supmemberid", "=", member.getId()).and("supwxopenid", "=", member.getWxopenid()).and("drawcashid", "is", null).and("drawcashcno", "is", null).and("state", "is", null));
		if(nums==0)
		{
			remap.put("state", "error");
			remap.put("message", "没有可提现积分，不能申请提现！");
			return remap;
		}
		
		String id = UUIDGenerator.getInstance().getNextValue();
		String cno = SNGenerator.getValue(8);
		
		DrawCash drawcash = new DrawCash();
		drawcash.setMemberid(member.getId());
		drawcash.setMemberwxopenid(member.getWxopenid());
		drawcash.setMembercno(member.getCno());
		drawcash.setMembercname(member.getCname());
		
		drawcash.setMemberphone(member.getPhone());
		drawcash.setAccounttype(member.getAccounttype());
		drawcash.setBank(member.getBank());
		drawcash.setOpenbank(member.getOpenbank());		
		drawcash.setBankaccountno(member.getBankaccountno());
		drawcash.setBankaccountcname(member.getBankaccountcname());
		drawcash.setBankaccountphone(member.getBankaccountphone());

		drawcash.setId(id);
		drawcash.setCno(cno);
		drawcash.setApplytime(new Timestamp(System.currentTimeMillis()));
		drawcash.setState(AppDrawCashService.state_apply);
		
		sdao().insert(drawcash);
		
		// 更新提现订单详细信息
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update t_app_ordergoodsrebate ");
		sql.append("    set state = ").append(SQLParser.charValueEnd(AppDrawCashService.state_apply)).append("\n");
		sql.append("  drawcashid = ").append(SQLParser.charValueEnd(drawcash.getId())).append("\n");
		sql.append("  drawcashcno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
		// sql.append("  drawapplytime = ").append(SQLParser.charValue(drawcash.getApplytime().toString())).append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and supmemberid = ").append(SQLParser.charValue(drawcash.getMemberid())).append("\n");
		sql.append("    and supwxopenid = ").append(SQLParser.charValue(drawcash.getMemberwxopenid())).append("\n");
		sql.append("    and drawcashid is null ").append("\n");
		sql.append("    and drawcashcno is null ").append("\n");
		sql.append("    and state is null ").append("\n");
		
		sdao().execute(Sqls.create(sql.toString()));
		
		sql = new StringBuffer();
		sql.append(" update t_app_drawcash ").append("\n");
		sql.append("   set amount = ");
		sql.append(" ( ").append("\n");
		sql.append(" select sum(score) score ").append("\n");
		sql.append("   from t_app_ordergoodsrebate ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and drawcashcno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
		sql.append("   and supmemberid = ").append(SQLParser.charValue(drawcash.getMemberid())).append("\n");
		sql.append("   and supwxopenid = ").append(SQLParser.charValue(drawcash.getMemberwxopenid())).append("\n");
		sql.append(" ) ").append("\n");
		sql.append(" where 1 = 1 ");
		sql.append("   and cno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
		
		sdao().execute(Sqls.create(sql.toString()));
		
		// 由于提现单总额是通过UPDATE语句直接更新的，并未同步至drawcash对象提现总额，因此通过重新读取数据库刷新订单总额数据
		drawcash = drawcashService.fetch(id);
		
		String bflowid = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_drawcash_name)).getId();
		String dataid = drawcash.getId();

		DynamicObject swapflow = new DynamicObject();
		swapflow.setAttr("bflowid", bflowid);
		swapflow.setAttr("dataid", dataid);

		String runflowkey = appflowService.create(swapflow, login_token);
		String nextstate = rflowService.locate(runflowkey).getFormatAttr("state");

		drawcash.setState(nextstate);
		sdao().update(drawcash);		
		
		remap.put("state", "success");
		remap.put("drawcash", drawcash);
		
		return remap;
	}	
	
	public void update_amount(String cno, DynamicObject login_token)
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update t_app_drawcash ").append("\n");
		sql.append("   set amount = ");
		sql.append(" ( ").append("\n");
		sql.append(" select sum(score) score ").append("\n");
		sql.append("   from t_app_ordergoodsrebate ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("   and drawcashcno = ").append(SQLParser.charValue(cno)).append("\n");
		sql.append("   and supmemberid = ").append(SQLParser.charValue(userid)).append("\n");
		sql.append("   and supwxopenid = ").append(SQLParser.charValue(userwxopenid)).append("\n");
		sql.append(" ) ").append("\n");
		sql.append(" where 1 = 1 ");
		sql.append("   and cno = ").append(SQLParser.charValue(cno)).append("\n");
		
		sdao().execute(Sqls.create(sql.toString()));
	}
	
//	public Map insert(DrawCash drawcash, DynamicObject login_token, String keysignature) throws Exception
//	{
//		// 检查签名正确性
//		
//		Map remap = new DynamicObject();
//
//		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
//		
//		// 检查会员信息是否完整，不完整不允许提交
//		AppMemberService memberService = new AppMemberService();
//		memberService.setDao(sdao());
//		remap = memberService.checkinfo(sdao().fetch(Member.class, userid), login_token);
//		if(!("success".equals(remap.get("state"))))
//		{
//			return remap;
//		}
//		
//		remap.clear();
//		
//		// 检查是否有未提现的订单商品，如果没有可提现的订单商品，不生成提现申请单。
//		int nums = sdao().count(OrderGoodsRebate.class, Cnd.where("supmemberid", "=", drawcash.getMemberid()).and("supwxopenid", "=", drawcash.getMemberwxopenid()).and("drawcashid", "is", null).and("drawcashcno", "is", null).and("state", "is", null));
//		if(nums==0)
//		{
//			remap.put("state", "error");
//			remap.put("message", "没有可提现积分，不能申请提现！");
//			return remap;
//		}
//		
//		String id = UUIDGenerator.getInstance().getNextValue();
//		String cno = SNGenerator.getValue(8);
//
//		Member member = sdao().fetch(Member.class, userid);
//		
//		drawcash.setMemberphone(member.getPhone());
//		drawcash.setAccounttype(member.getAccounttype());
//		drawcash.setBank(member.getBank());
//		drawcash.setBankaccountno(member.getBankaccountno());
//		drawcash.setBankaccountcname(member.getBankaccountcname());
//		drawcash.setBankaccountphone(member.getBankaccountphone());
//		
//		drawcash.setId(id);
//		drawcash.setCno(cno);
//		drawcash.setApplytime(new Timestamp(System.currentTimeMillis()));
//		drawcash.setState(AppDrawCashService.state_apply);
//		
//		sdao().insert(drawcash);
//		
//		// 更新提现订单详细信息
//		StringBuffer sql = new StringBuffer();
//		
//		sql.append(" update t_app_ordergoodsrebate ");
//		sql.append("    set state = ").append(SQLParser.charValueEnd(AppDrawCashService.state_apply)).append("\n");
//		sql.append("  drawcashid = ").append(SQLParser.charValueEnd(drawcash.getId())).append("\n");
//		sql.append("  drawcashcno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
//		// sql.append("  drawapplytime = ").append(SQLParser.charValue(drawcash.getApplytime().toString())).append("\n");
//		sql.append("  where 1 = 1 ").append("\n");
//		sql.append("    and supmemberid = ").append(SQLParser.charValue(drawcash.getMemberid())).append("\n");
//		sql.append("    and supwxopenid = ").append(SQLParser.charValue(drawcash.getMemberwxopenid())).append("\n");
//		sql.append("    and drawcashid is null ").append("\n");
//		sql.append("    and drawcashcno is null ").append("\n");
//		sql.append("    and state is null ").append("\n");
//		
//		sdao().execute(Sqls.create(sql.toString()));
//		
//		sql = new StringBuffer();
//		sql.append(" update t_app_drawcash ").append("\n");
//		sql.append("   set amount = ");
//		sql.append(" ( ").append("\n");
//		sql.append(" select sum(score) score ").append("\n");
//		sql.append("   from t_app_ordergoodsrebate ").append("\n");
//		sql.append(" where 1 = 1 ").append("\n");
//		sql.append("   and drawcashcno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
//		sql.append("   and supmemberid = ").append(SQLParser.charValue(drawcash.getMemberid())).append("\n");
//		sql.append("   and supwxopenid = ").append(SQLParser.charValue(drawcash.getMemberwxopenid())).append("\n");
//		sql.append(" ) ").append("\n");
//		sql.append(" where 1 = 1 ");
//		sql.append("   and cno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
//		
//		sdao().execute(Sqls.create(sql.toString()));
//		
//		String bflowid = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_drawcash_name)).getId();
//		String dataid = drawcash.getId();
//
//		DynamicObject swapflow = new DynamicObject();
//		swapflow.setAttr("bflowid", bflowid);
//		swapflow.setAttr("dataid", dataid);
//
//		String runflowkey = appflowService.create(swapflow, login_token);
//		String nextstate = rflowService.locate(runflowkey).getFormatAttr("state");
//
//		drawcash.setState(nextstate);
//		sdao().update(drawcash);		
//		
//		remap.put("state", "success");
//		remap.put("drawcash", drawcash);
//		
//		return remap;
//	}
	
	// 转发
	public Map forward(DrawCash drawcash, DynamicObject login_token) throws Exception
	{
		String id = drawcash.getId();
		
		Map map = new DynamicObject();
		DynamicObject order = drawcashService.locate(id);

		// 更新流程信息
		String dataid = order.getFormatAttr("id");
		String bflowid = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_drawcash_name)).getId();
		RFlow rflow = rflowService.fetch(Cnd.where("dataid", "=", dataid).and("bflowid", "=", bflowid));
		String runflowkey = rflow.getRunflowkey();
		String sname = rflow.getState();

		DynamicObject swapflow = new DynamicObject();
		swapflow.setAttr("runflowkey", runflowkey);
		swapflow.setAttr("sname", sname);

		String flownextstate = appflowService.forward(swapflow, login_token);

		// 更新状态至下一环节
		sdao().update(DrawCash.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));

		map.put("state", "success");
		map.put("flownextstate", flownextstate);

		return map;
	}
	
	public Map checkinfo(DrawCash drawcash, DynamicObject login_token) throws Exception
	{
		Map remap = new DynamicObject();
		
		// 检查修改会员是否当前会员
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		
		if(StringToolKit.isBlank(userid))
		{
			remap.put("state", "error");
			remap.put("message", "用户会话无效，请重新登录！");
			return remap;
			// throw new Exception("用户会话无效，请重新登录！");
		}
		if(!userid.equals(drawcash.getMemberid()))
		{
			remap.put("state", "error");
			remap.put("message", "当前会员与要提现的会员不一致，不允许提现！");
			return remap;
			// throw new Exception("当前会员与要修改的会员不一致，不允许修改会员资料！");
		};
		
		if(StringToolKit.isBlank(drawcash.getMemberid()))
		{
			remap.put("state", "error");
			remap.put("message", "会员信息异常，无法提现！");
			return remap;
			// throw new Exception("会员数据异常，无法保存，请联系客服！");
		}
		
		if(StringToolKit.isBlank(drawcash.getMembercname()))
		{
			remap.put("state", "error");
			remap.put("message", "会员姓名为空，不允许提现！");	
			return remap;
			// throw new Exception("姓名不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(drawcash.getMemberphone()))
		{
			remap.put("state", "error");
			remap.put("message", "会员电话为空，不允许提现！");	
			return remap;	
			// throw new Exception("电话不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(drawcash.getOpenbank()))
		{
			remap.put("state", "error");
			remap.put("message", "开户银行不允许为空，重新申请提现！");	
			return remap;
			// throw new Exception("开户银行不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(drawcash.getBankaccountno()))
		{
			remap.put("state", "error");
			remap.put("message", "银行卡号不允许为空，重新申请提现！");	
			return remap;
			// throw new Exception("银行卡号不允许为空，请填写完整！");
		}
		
		if(StringToolKit.isBlank(drawcash.getBankaccountcname()))
		{
			remap.put("state", "error");
			remap.put("message", "账户名不允许为空，重新申请提现！");	
			return remap;
			// throw new Exception("账户名不允许为空，请填写完整！");
		}
		return remap;
	}
	
	
	
}
