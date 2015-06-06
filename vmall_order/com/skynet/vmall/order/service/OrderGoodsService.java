package com.skynet.vmall.order.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.OrderGoods;

@InjectName("ordergoodsService")
@IocBean(args =
{ "refer:dao" })
public class OrderGoodsService extends SkynetNameEntityService<OrderGoods>
{
	public OrderGoodsService()
	{
		super();
	}

	public OrderGoodsService(Dao dao)
	{
		super(dao);
	}

	public OrderGoodsService(Dao dao, Class<OrderGoods> entityType)
	{
		super(dao, entityType);
	}

}
