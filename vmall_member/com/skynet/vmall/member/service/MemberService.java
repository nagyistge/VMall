package com.skynet.vmall.member.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.User;
import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.common.generator.RandomGenerator;
import com.skynet.framework.common.generator.SNGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.GeneratorService;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Follow;
import com.skynet.vmall.base.pojo.Member;

@InjectName("memberService")
@IocBean(args =
{ "refer:dao" })
public class MemberService extends SkynetNameEntityService<Member>
{
	public static int level_rebate = 3; // 返利总计层级数
	
	@Inject
	GeneratorService generatorService;

	public MemberService()
	{
		super();
	}

	public MemberService(Dao dao)
	{
		super(dao);
	}

	public MemberService(Dao dao, Class<Member> entityType)
	{
		super(dao, entityType);
	}
	
	public DynamicObject newwxuser(String oldwxopenid, String newwxopenid) throws Exception
	{
		// 如果没有推荐人，该用户直接加入至商城下账号
		if(StringToolKit.isBlank(oldwxopenid))
		{
			Member vmall = sdao().fetch(Member.class, Cnd.where("supid", "=", "R0"));
			if(vmall!=null)
			{
				oldwxopenid = vmall.getWxopenid();
			}
		}
		
		// 检查推荐人是否正常
		Member oldmember = sdao().fetch(Member.class, Cnd.where("wxopenid", "=", oldwxopenid));
		if(oldmember==null||StringToolKit.isBlank(oldmember.getId()))
		{
			return null;
		}
		
		// 检查被推荐人是否新微信用户
		Member newmember = sdao().fetch(Member.class, Cnd.where("wxopenid", "=", newwxopenid));
		if(newmember==null||StringToolKit.isBlank(newmember.getId()))
		{
			String cno = SNGenerator.getValue(8);
			User newuser = new User();
			String newuserid = UUIDGenerator.getInstance().getNextValue();
			newuser.setId(newuserid);
			newuser.setWxopenid(newwxopenid);
			newuser.setLoginname(cno);
			newuser.setCreatetime(new Timestamp(System.currentTimeMillis()));
			newuser.setPassword("1111"); // 上线前改为加密
			sdao().insert(newuser);
			
			newmember = new Member();
			newmember.setId(newuserid);
			newmember.setSupid(oldmember.getId());
			newmember.setInternal(oldmember.getInternal() + FormatKey.format(RandomGenerator.getValue(4), 4));
			newmember.setLevel(oldmember.getLevel() + 1);
			newmember.setWxopenid(newwxopenid);
			newmember.setCno(cno);
			newmember.setCreatetime(newuser.getCreatetime());
			newmember.setCtype("会员");
			newmember.setScore(0);
			sdao().insert(newmember);
		}
		
		follow(newwxopenid, null, oldwxopenid, null); //记录关注信息
		
		DynamicObject user = sdao().locateBy("t_sys_user", Cnd.where("id", "=", newmember.getId()));

		DynamicObject obj = new DynamicObject();
		obj.setAttr(GlobalConstants.sys_login_user, user.getFormatAttr("loginname"));
		obj.setAttr(GlobalConstants.sys_login_username, user.getFormatAttr("cname"));
		obj.setAttr(GlobalConstants.sys_login_userid, user.getFormatAttr("id"));
		obj.setAttr(GlobalConstants.sys_login_userwxopenid, user.getFormatAttr("wxopenid"));
		
		return obj;
	}

	// 关注
	// swxopenid 关注人微信标识（关注别人 新会员 粉丝）
	// dwxopenid 被关注人微信标识（被别人关注 老会员 偶像）
	public void follow(String swxopenid, String swxnickname, String dwxopenid, String dwxnickname) throws Exception
	{
		// 检查源微信用户是否还未成为会员，创建会员信息，会员不允许再关注。
		// 检查目标微信用户是否已经是会员，如果不是，不允许关注
		// 检查源微信用户是否已经关注过微信用户，不允许关注1个以上目标微信用户
		// 检查是否已经关注过目标微信用户
		// 检查是否反向关注过源微信用户(不可反向关注)
		// 未关注过，则建立关注
//		if (sdao().count(Member.class, Cnd.where("wxopenid", "=", swxopenid)) > 0)
//		{
//			// throw new Exception("当前用户已经是会员，不允许重复关注其它会员！");
//			return;
//		}

		if (sdao().count(Member.class, Cnd.where("wxopenid", "=", dwxopenid)) == 0)
		{
			// throw new Exception("要关注的用户不是会员，不允许关注！");
			return;
		}

		if (sdao().count(Follow.class, Cnd.where("swxopenid", "=", swxopenid)) > 0)
		{
			// throw new Exception("你已关注过其它用户，不允许再关注！");
			return;
		}

		if (sdao().count(Follow.class, Cnd.where("swxopenid", "=", swxopenid).and("dwxopenid", "=", dwxopenid)) > 0)
		{
			// throw new Exception("已关注过该用户，不允许重复关注！");
			return;
		}

		if (sdao().count(Follow.class, Cnd.where("swxopenid", "=", dwxopenid).and("dwxopenid", "=", swxopenid)) > 0)
		{
			// throw new Exception("该用户已关注过你，不允许再进行关注！");
			return;
		}

		Member supmember = sdao().fetch(Member.class, Cnd.where("wxopenid", "=", dwxopenid)); // 老会员
		Member submember = sdao().fetch(Member.class, Cnd.where("wxopenid", "=", swxopenid)); // 新会员
		
		Follow follow = new Follow();
		String followid = UUIDGenerator.getInstance().getNextValue();
		follow.setId(followid);
		follow.setSwxopenid(swxopenid);
		follow.setDwxopenid(dwxopenid);
		follow.setSmembercno(submember.getCno());
		follow.setDmembercno(supmember.getCno());
		follow.setFollowtime(new Timestamp(System.currentTimeMillis()));

		sdao().insert(follow);
	}

	// 生成会员内部编码
	public String gen_internal(String supid) throws Exception
	{
		String internal = new String();
		if (!supid.equals("R0"))

		{
			Member supmember = sdao().fetch(Member.class, supid);
			internal = supmember.getInternal();
		}
		else
		{
			internal = "0000";
		}

		Map map = new HashMap();
		map.put("field_names", new String[]
		{ "internal", "supid" });
		map.put("field_values", new String[]
		{ internal, supid });

		String csql = " select substring(max(internal),length(max(internal))-3, 4) as internal from t_app_member where supid = :supid";
		String express = "$internal####";

		map.put("csql", csql);
		map.put("express", express);

		return generatorService.getNextValue(map);
	}

	// 查找给定层级数范围内上级会员
	public List<DynamicObject> findsupmembers(String memberid, int findlevel) throws Exception
	{
		String internal = locate(memberid).getFormatAttr("internal");
		List<DynamicObject> supmembers = new ArrayList<DynamicObject>();
		int len = internal.length();
		for (int i = 1; i <= findlevel; i++)
		{
			int last = len - (i * 4);
			if(last>=0)
			{
				DynamicObject supmember = locateBy(Cnd.where("internal", "=", internal.substring(0, last)));
				if(StringToolKit.isBlank(supmember.getFormatAttr("id")))
				{
					break;
				}
				else
				{
					supmembers.add(supmember);
				}
			}
		}
		return supmembers;
	}
	
	// 查找给定层级数范围内下级会员
	public List<DynamicObject> findsubmembers(String memberid, int findlevel) throws Exception
	{
		String internal = locate(memberid).getFormatAttr("internal");
		int len = internal.length();
		int minlen = len + 4;
		if(minlen<0)
		{
			minlen = 0;
		}
		int maxlen = len + (findlevel * 4);
		if(maxlen<0)
		{
			maxlen = 0;
		}
		List<DynamicObject> submembers = new ArrayList<DynamicObject>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_member ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and internal like '"+internal+"%' ").append("\n");
		sql.append("    and length(internal) >= " + minlen).append("\n");
		sql.append("    and length(internal) <= " + maxlen).append("\n");
		sql.append("  order by internal ").append("\n");
		
		submembers = sdao().queryForList(sql.toString());
		
		return submembers;
	}
	
	// 浏览我的订单
	public List<DynamicObject> showmyorder(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_order ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and memberid = ").append(SQLParser.charValue(memberid)).append("\n");
		// 增加查询过滤条件
		sql.append("  order by ordertime desc ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	// 浏览我的订单商品明细
	public List<DynamicObject> showmyordergoods(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String id = (String)map.get("id");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_ordergoods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and orderid = ").append(SQLParser.charValue(id)).append("\n");
		// 增加查询过滤条件
		List<DynamicObject> datas = sdao().queryForList(sql.toString());

		return datas;
	}
	
	// 浏览我的提现
	public List<DynamicObject> browsemydraw(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_drawcash ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and memberid = ").append(SQLParser.charValue(memberid)).append("\n");
		// 增加查询过滤条件
		sql.append("  order by applytime desc ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	// 我的积分
	public BigDecimal myrebateshowsum(Map map) throws Exception
	{
		String memberid = (String)map.get("memberid");
		String rebatetimebegin = (String)map.get("rebatetimebegin");
		String rebatetimeend = (String)map.get("rebatetimeend");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(score) score ").append("\n");
		sql.append(" from t_app_ordergoodsrebate rebate ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		if(!StringToolKit.isBlank(rebatetimebegin))
		{
			sql.append(" and rebate.rebatetime >= ").append(SQLParser.charValue(rebatetimebegin));
		}
		
		if(!StringToolKit.isBlank(rebatetimeend))
		{
			sql.append(" and rebate.rebatetime < ").append(SQLParser.charValue(rebatetimeend));
		}
		
		int score = Types.parseInt(queryForMap(sql.toString()).getFormatAttr("score"), 0);
		BigDecimal sumscore = new BigDecimal(score).setScale(2);
		return sumscore;		
	}

	public List<DynamicObject> myrebateshowbygroup(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select rebate.submemberid, member.cno, member.cname, sum(rebate.score) score ").append("\n");
		sql.append("   from t_app_ordergoodsrebate rebate, t_app_member member ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.submemberid = member.id").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("  group by rebate.submemberid, member.cno, member.cname ").append("\n");
		sql.append("  order by member.cname, member.cno desc ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;		
	}
	
	public List<DynamicObject> myrebateshowbygoods(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select rebate.submemberid, goods.id goodsid, goods.cname, sum(rebate.score) score ").append("\n");
		sql.append("   from t_app_ordergoodsrebate rebate, t_app_ordergoods ordergoods, t_app_goods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.ordergoodsid = ordergoods.id ").append("\n");
		sql.append("    and ordergoods.goodsid = goods.id ").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("  group by rebate.submemberid, goods.id, goods.cname ").append("\n");
		sql.append("  order by goods.cname desc ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;		
	}
	
	public List<DynamicObject> myrebateshowbyorder(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select rebate.submemberid, morder.id orderid, morder.cno, morder.ordertime, sum(rebate.score) score ").append("\n");
		sql.append("   from t_app_ordergoodsrebate rebate, t_app_ordergoods ordergoods, t_app_order morder ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.ordergoodsid = ordergoods.id ").append("\n");
		sql.append("    and ordergoods.orderid = morder.id ").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("  group by rebate.submemberid, morder.id, morder.cno, morder.ordertime ").append("\n");
		sql.append("  order by morder.ordertime desc ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;		
	}
	
	public Map saveinfo(Member newmember, DynamicObject login_token) throws Exception
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
		if(!userid.equals(newmember.getId()))
		{
			remap.put("state", "error");
			remap.put("message", "当前会员与要修改的会员不一致，不允许修改会员资料！");
			return remap;
			// throw new Exception("当前会员与要修改的会员不一致，不允许修改会员资料！");
		};
		
		if(StringToolKit.isBlank(newmember.getId()))
		{
			remap.put("state", "error");
			remap.put("message", "会员数据异常，无法保存，请联系客服！");
			return remap;
			// throw new Exception("会员数据异常，无法保存，请联系客服！");
		}
		
		if(StringToolKit.isBlank(newmember.getCname()))
		{
			remap.put("state", "error");
			remap.put("message", "姓名不允许为空，请填写完整！");	
			return remap;
			// throw new Exception("姓名不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(newmember.getPhone()))
		{
			remap.put("state", "error");
			remap.put("message", "电话不允许为空，请填写完整！");	
			return remap;	
			// throw new Exception("电话不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(newmember.getAddr()))
		{
			remap.put("state", "error");
			remap.put("message", "详细地址不允许为空，请填写完整！");	
			return remap;
			// throw new Exception("详细地址不允许为空，请填写完整！");
		}		
		
		if(StringToolKit.isBlank(newmember.getOpenbank()))
		{
			remap.put("state", "error");
			remap.put("message", "开户银行不允许为空，请填写完整！");	
			return remap;
			// throw new Exception("开户银行不允许为空，请填写完整！");
		}

		if(StringToolKit.isBlank(newmember.getBankaccountno()))
		{
			remap.put("state", "error");
			remap.put("message", "银行卡号不允许为空，请填写完整！");	
			return remap;
			// throw new Exception("银行卡号不允许为空，请填写完整！");
		}
		
		if(StringToolKit.isBlank(newmember.getBankaccountcname()))
		{
			remap.put("state", "error");
			remap.put("message", "账户名不允许为空，请填写完整！");	
			return remap;
			// throw new Exception("账户名不允许为空，请填写完整！");
		}
		
		Member member = sdao().fetch(Member.class, userid);
		member.setCname(newmember.getCname());
		member.setPhone(newmember.getPhone());
		member.setQq(newmember.getQq());
		member.setEmail(newmember.getEmail());
		
		member.setProvince(newmember.getProvince());
		member.setCity(newmember.getCity());
		member.setCounty(newmember.getCounty());
		member.setTown(newmember.getTown());
		member.setAddr(newmember.getAddr());
		member.setPostcode(newmember.getPostcode());

		member.setBank(newmember.getBank());
		member.setBankaccountno(newmember.getBankaccountno());
		member.setBankaccountcname(newmember.getBankaccountcname());
		member.setBankaccountphone(newmember.getBankaccountphone());
		
		sdao().update(member);
		
		remap.put("state", "success");
		remap.put("member", locate(userid));
		
		return remap;
	}
	
}
