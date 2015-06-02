package com.skynet.vmall.member.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.User;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.GeneratorService;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Follow;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.pojo.TreeMember;

@InjectName("memberService")
@IocBean(args =
{ "refer:dao" })
public class MemberService extends SkynetNameEntityService<Member>
{
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

	// 关注
	public void follow(String swxopenid, String swxnickname, String dwxopenid, String dwxnickname) throws Exception
	{
		// 检查源微信用户是否还未成为会员，创建会员信息，会员不允许再关注。
		// 检查目标微信用户是否已经是会员，如果不是，不允许关注
		// 检查源微信用户是否已经关注过微信用户，不允许关注1个以上目标微信用户
		// 检查是否已经关注过目标微信用户
		// 检查是否反向关注过源微信用户(不可反向关注)
		// 未关注过，则建立关注
		if(sdao().count(Member.class, Cnd.where("wxopenid", "=", swxopenid))>0)
		{
			throw new Exception("当前用户已经是会员，不允许重复关注其它会员！");
		}
		
		if(sdao().count(Member.class, Cnd.where("wxopenid", "=", dwxopenid))==0)
		{
			throw new Exception("要关注的用户不是会员，不允许关注！");
		}
		
		if(sdao().count(Follow.class, Cnd.where("swxopenid", "=", swxopenid))>0)
		{
			throw new Exception("你已关注过其它用户，不允许再关注！");
		}

		if(sdao().count(Follow.class, Cnd.where("swxopenid", "=", swxopenid).and("dwxopenid", "=", dwxopenid))>0)
		{
			throw new Exception("已关注过该用户，不允许重复关注！");
		}

		if(sdao().count(Follow.class, Cnd.where("swxopenid", "=", dwxopenid).and("dwxopenid", "=", swxopenid))>0)
		{
			throw new Exception("该用户已关注过你，不允许再进行关注！");
		}
		
		String id = UUIDGenerator.getInstance().getNextValue();
		Date cdate = new Date();
		User user = new User();
		user.setId(id);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sf.format(cdate);
		String sno = String.valueOf((Math.random() * 100000)/1);
		user.setCname("用户_" + sdate + sno);
		user.setWxopenid(swxopenid);
		user.setWxnickname(swxnickname);
		user.setCreatetime(new Timestamp(cdate.getTime()));
		sdao().insert(user);
		
		Member supmember = sdao().fetch(Member.class, Cnd.where("wxopenid", "=", dwxopenid));

		Member member = new Member();
		member.setId(id);
		member.setCname(user.getCname());
		member.setWxopenid(swxopenid);
		member.setWxnickname(swxnickname);
		member.setInternal(supmember.getInternal() + TreeMember.single_internal);
		member.setLevel(supmember.getLevel() + 1);
		member.setSupid(supmember.getId());
		
		sdao().insert(member);
		
		Follow follow = new Follow();
		String followid = UUIDGenerator.getInstance().getNextValue();
		follow.setId(followid);
		follow.setSwxopenid(swxopenid);
		follow.setDwxopenid(dwxopenid);
		follow.setFollowtime(new Timestamp(cdate.getTime()));
		
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
	
}
