package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.AttachRef;

@InjectName("attachrefService")
@IocBean(args =
{ "refer:dao" })
public class AttachRefService extends SkynetNameEntityService<AttachRef>
{
	public AttachRefService()
	{
		super();
	}

	public AttachRefService(Dao dao)
	{
		super(dao);
	}

	public AttachRefService(Dao dao, Class<AttachRef> entityType)
	{
		super(dao, entityType);
	}
}
