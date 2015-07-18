package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;

@InjectName("ordergoodsrebateService")
@IocBean(args =
{ "refer:dao" })
public class OrderGoodsRebateService extends SkynetNameEntityService<OrderGoodsRebate>
{
	public OrderGoodsRebateService()
	{
		super();
	}

	public OrderGoodsRebateService(Dao dao)
	{
		super(dao);
	}

	public OrderGoodsRebateService(Dao dao, Class<OrderGoodsRebate> entityType)
	{
		super(dao, entityType);
	}

}
