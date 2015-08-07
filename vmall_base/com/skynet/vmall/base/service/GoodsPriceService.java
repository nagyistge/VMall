package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsPrice;

@InjectName("goodspriceService")
@IocBean(args =
{ "refer:dao" })
public class GoodsPriceService extends SkynetNameEntityService<GoodsPrice>
{
	public GoodsPriceService()
	{
		super();
	}

	public GoodsPriceService(Dao dao)
	{
		super(dao);
	}

	public GoodsPriceService(Dao dao, Class<GoodsPrice> entityType)
	{
		super(dao, entityType);
	}
}
