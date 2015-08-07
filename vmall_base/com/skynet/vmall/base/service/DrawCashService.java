package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.DrawCash;

@InjectName("drawcashService")
@IocBean(args =
{ "refer:dao" })
public class DrawCashService extends SkynetNameEntityService<DrawCash>
{
	public DrawCashService()
	{
		super();
	}

	public DrawCashService(Dao dao)
	{
		super(dao);
	}

	public DrawCashService(Dao dao, Class<DrawCash> entityType)
	{
		super(dao, entityType);
	}
}
