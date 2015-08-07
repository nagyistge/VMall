package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Goods;

@InjectName("goodsService")
@IocBean(args =
{ "refer:dao" })
public class GoodsService extends SkynetNameEntityService<Goods>
{
	public GoodsService()
	{
		super();
	}

	public GoodsService(Dao dao)
	{
		super(dao);
	}

	public GoodsService(Dao dao, Class<Goods> entityType)
	{
		super(dao, entityType);
	}
}
