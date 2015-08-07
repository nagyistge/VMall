package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.EventItem;

@InjectName("eventitemService")
@IocBean(args =
{ "refer:dao" })
public class EventItemService extends SkynetNameEntityService<EventItem>
{
	public EventItemService()
	{
		super();
	}

	public EventItemService(Dao dao)
	{
		super(dao);
	}

	public EventItemService(Dao dao, Class<EventItem> entityType)
	{
		super(dao, entityType);
	}
}
