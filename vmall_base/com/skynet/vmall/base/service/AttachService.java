package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Attach;

@InjectName("attachService")
@IocBean(args =
{ "refer:dao" })
public class AttachService extends SkynetNameEntityService<Attach>
{
	public AttachService()
	{
		super();
	}

	public AttachService(Dao dao)
	{
		super(dao);
	}

	public AttachService(Dao dao, Class<Attach> entityType)
	{
		super(dao, entityType);
	}
}
