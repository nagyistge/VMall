package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.EventItemGoods;

@InjectName("eventitemgoodsService")
@IocBean(args =
{ "refer:dao" })
public class EventItemGoodsService extends SkynetNameEntityService<EventItemGoods>
{
	public EventItemGoodsService()
	{
		super();
	}

	public EventItemGoodsService(Dao dao)
	{
		super(dao);
	}

	public EventItemGoodsService(Dao dao, Class<EventItemGoods> entityType)
	{
		super(dao, entityType);
	}
}
