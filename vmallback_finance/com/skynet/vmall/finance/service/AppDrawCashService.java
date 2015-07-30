package com.skynet.vmall.finance.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.RFlow;
import com.skynet.app.flow.service.AppFlowService;
import com.skynet.app.flow.service.BFlowService;
import com.skynet.app.flow.service.RFlowService;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.base.query.QueryHelper;
import com.skynet.vmall.base.service.DrawCashService;
import com.skynet.vmall.base.service.OrderGoodsRebateService;

@InjectName("appdrawcashService")
@IocBean(args =
{ "refer:dao" })
public class AppDrawCashService extends SkynetDaoService
{
	@Inject
	AppFlowService appflowService;

	@Inject
	BFlowService bflowService;

	@Inject
	RFlowService rflowService;
	
	@Inject
	DrawCashService drawcashService;
	
	@Inject
	OrderGoodsRebateService ordergoodsrebateService;	

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

	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = (Integer) map.get("_page");
		int pagesize = (Integer) map.get("_pagesize");

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		String state = (String) map.get("state");
		String mobile_cno = (String) map.get("mobile_cno");
		String goodscode = (String) map.get("goods_no");
		String applytimebegin = (String) map.get("applytimebegin");
		String applytimeend = (String) map.get("applytimeend");

		StringBuffer sql = new StringBuffer();
		sql.append(" select draw.*, member.wxnickname ");
		sql.append("  from t_app_drawcash draw, t_app_member member ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("   and draw.memberid = member.id ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(mobile_cno))
		{
			sql.append("  and ( ");
			sql.append("  membercname like ").append(SQLParser.charLikeValue(mobile_cno)).append("\n");
			sql.append("  or memberphone like ").append(SQLParser.charLikeValue(mobile_cno)).append("\n");
			sql.append("  or cno like ").append(SQLParser.charLikeValue(mobile_cno)).append("\n");
			sql.append("  ) ").append("\n");
		}

		if (!StringToolKit.isBlank(state))
		{
			sql.append("  and drawstate = ").append(SQLParser.charValue(state)).append("\n");
		}

		if (!StringToolKit.isBlank(applytimebegin))
		{
			sql.append("  and datediff(applytime, '" + applytimebegin + "')>=0").append("\n");
		}
		
		if (!StringToolKit.isBlank(applytimeend))
		{
			sql.append("  and datediff(applytime, '" + applytimeend + "')<=0").append("\n");
		}
		
		if (!StringToolKit.isBlank(goodscode))
		{
//			sql.append("  and exists( ").append("\n");
//			sql.append("  select id from t_app_ordergoods ").append("\n");
//			sql.append("   where orderid = drawid ").append("\n");
//			sql.append("     and ( ").append("\n");
//			sql.append("         goodscode like ").append(SQLParser.charLikeValue(goodscode)).append("\n");
//			sql.append("      or goodsname like ").append(SQLParser.charLikeValue(goodscode)).append("\n");
//			sql.append("     )").append("\n");
//			sql.append(" ) ").append("\n");
		}

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}
	
	public List<DynamicObject> listgoodsrebate(String drawcashid, String memberid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select rebate.* ");
		sql.append("   from t_app_ordergoodsrebate rebate ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.drawcashid = " + SQLParser.charValue(drawcashid)).append("\n");
		// sql.append("    and rebate.supmemberid = draw.memberid ").append("\n");	//此条件可以不用	
		List datas = ordergoodsrebateService.queryForList(sql.toString());
		return datas;
	}

	// 转发
	public Map foward(DynamicObject form, DynamicObject login_token) throws Exception
	{
		String id = form.getAttr("id");
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
		String flownextstate = sname;
		try
		{
			flownextstate = appflowService.forward(swapflow, login_token);

			// 更新状态至下一环节
			sdao().update(DrawCash.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));
		}
		catch(Exception e)
		{
			DynamicObject ro = new DynamicObject();
			ro.setAttr("state", "error");
			ro.setAttr("message", e.getMessage());
			return ro;
		}
		
		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setAttr("flownextstate", flownextstate);
		return ro;
	}
	
	// 转发
	public Map savepay(Map form, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		String id = (String)form.get("id");
		String payaccounttype = (String)form.get("payaccounttype");
		String payaccountno = (String)form.get("payaccountno");		
		String paybillcno = (String)form.get("paybillcno");
		Timestamp paytime = Timestamp.valueOf((String)form.get("paytime"));
		DrawCash drawcash = drawcashService.fetch(id);
		
		drawcash.setPayaccounttype(payaccounttype);
		drawcash.setPaybillcno(paybillcno);
		drawcash.setPaytime(paytime);
		drawcash.setPayaccountno(payaccountno);
		drawcash.setPaystate("已支付");
		
		drawcash.setHandler(userid);
		drawcash.setHandlercname(username);
		drawcash.setHandletime(new Timestamp(System.currentTimeMillis()));
		
		sdao().update(drawcash);
		
		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");

		return ro;
	}


	


}
