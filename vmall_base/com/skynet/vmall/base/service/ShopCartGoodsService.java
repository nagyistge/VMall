package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.ShopCartGoods;

@InjectName("shopcartgoodsService")
@IocBean(args =
{ "refer:dao" })
public class ShopCartGoodsService extends SkynetNameEntityService<ShopCartGoods>
{
	public ShopCartGoodsService()
	{
		super();
	}

	public ShopCartGoodsService(Dao dao)
	{
		super(dao);
	}

	public ShopCartGoodsService(Dao dao, Class<ShopCartGoods> entityType)
	{
		super(dao, entityType);
	}

}
