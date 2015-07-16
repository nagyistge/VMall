package com.skynet.app.flow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.BAct;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("bactService")
@IocBean(args =
{ "refer:dao" })
public class BActService extends SkynetNameEntityService<BAct>
{
	public BActService()
	{
		super();
	}

	public BActService(Dao dao)
	{
		super(dao);
	}

	public BActService(Dao dao, Class<BAct> entityType)
	{
		super(dao, entityType);
	}
}
