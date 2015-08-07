package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsSpecProduct;

@InjectName("goodsspecproductService")
@IocBean(args =
{ "refer:dao" })
public class GoodsSpecProudctService extends SkynetNameEntityService<GoodsSpecProduct>
{
	public GoodsSpecProudctService()
	{
		super();
	}

	public GoodsSpecProudctService(Dao dao)
	{
		super(dao);
	}

	public GoodsSpecProudctService(Dao dao, Class<GoodsSpecProduct> entityType)
	{
		super(dao, entityType);
	}
}
