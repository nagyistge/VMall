package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsSpecValue;

@InjectName("goodsspecvalueService")
@IocBean(args =
{ "refer:dao" })
public class GoodsSpecValueService extends SkynetNameEntityService<GoodsSpecValue>
{
	public GoodsSpecValueService()
	{
		super();
	}

	public GoodsSpecValueService(Dao dao)
	{
		super(dao);
	}

	public GoodsSpecValueService(Dao dao, Class<GoodsSpecValue> entityType)
	{
		super(dao, entityType);
	}
}
