package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.MaterialItem;

@InjectName("materialitemService")
@IocBean(args =
{ "refer:dao" })
public class MaterialItemService extends SkynetNameEntityService<MaterialItem>
{
	public MaterialItemService()
	{
		super();
	}

	public MaterialItemService(Dao dao)
	{
		super(dao);
	}

	public MaterialItemService(Dao dao, Class<MaterialItem> entityType)
	{
		super(dao, entityType);
	}
}
