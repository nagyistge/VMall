package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Member;

@InjectName("memberService")
@IocBean(args =
{ "refer:dao" })
public class MemberService extends SkynetNameEntityService<Member>
{
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
}
