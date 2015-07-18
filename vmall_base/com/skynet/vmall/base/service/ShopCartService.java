package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.ShopCart;

@InjectName("shopcartService")
@IocBean(args =
{ "refer:dao" })
public class ShopCartService extends SkynetNameEntityService<ShopCart>
{
	public ShopCartService()
	{
		super();
	}

	public ShopCartService(Dao dao)
	{
		super(dao);
	}

	public ShopCartService(Dao dao, Class<ShopCart> entityType)
	{
		super(dao, entityType);
	}
}
