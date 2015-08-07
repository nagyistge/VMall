package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Order;

@InjectName("orderService")
@IocBean(args =
{ "refer:dao" })
public class OrderService extends SkynetNameEntityService<Order>
{
	public OrderService()
	{
		super();
	}

	public OrderService(Dao dao)
	{
		super(dao);
	}

	public OrderService(Dao dao, Class<Order> entityType)
	{
		super(dao, entityType);
	}
}
