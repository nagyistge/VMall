package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Material;

@InjectName("materialService")
@IocBean(args =
{ "refer:dao" })
public class MaterialService extends SkynetNameEntityService<Material>
{
	public MaterialService()
	{
		super();
	}

	public MaterialService(Dao dao)
	{
		super(dao);
	}

	public MaterialService(Dao dao, Class<Material> entityType)
	{
		super(dao, entityType);
	}
}
