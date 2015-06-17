package com.skynet.app.log.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.log.pojo.Log;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("logService")
@IocBean(args = { "refer:dao" }) 
public class LogService extends SkynetNameEntityService<Log>
{
	public LogService()
	{
		super();
	}
	
	public LogService(Dao dao)
	{
		super(dao);
	}	
	
	public LogService(Dao dao, Class<Log> entityType)
	{
		super(dao, entityType);
	}
}
