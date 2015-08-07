package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Event;

@InjectName("eventService")
@IocBean(args =
{ "refer:dao" })
public class EventService extends SkynetNameEntityService<Event>
{
	public EventService()
	{
		super();
	}

	public EventService(Dao dao)
	{
		super(dao);
	}

	public EventService(Dao dao, Class<Event> entityType)
	{
		super(dao, entityType);
	}
}
