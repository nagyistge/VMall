package com.skynet.app.flow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.RAct;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("ractService")
@IocBean(args =
{ "refer:dao" })
public class RActService extends SkynetNameEntityService<RAct>
{
	public RActService()
	{
		super();
	}

	public RActService(Dao dao)
	{
		super(dao);
	}

	public RActService(Dao dao, Class<RAct> entityType)
	{
		super(dao, entityType);
	}
}
