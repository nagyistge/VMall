package com.skynet.app.flow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.FlowLog;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("flowlogService")
@IocBean(args =
{ "refer:dao" })
public class FlowLogService extends SkynetNameEntityService<FlowLog>
{
	public FlowLogService()
	{
		super();
	}

	public FlowLogService(Dao dao)
	{
		super(dao);
	}

	public FlowLogService(Dao dao, Class<FlowLog> entityType)
	{
		super(dao, entityType);
	}
}
