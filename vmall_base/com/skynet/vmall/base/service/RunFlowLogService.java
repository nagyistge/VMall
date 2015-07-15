package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.RunFlowLog;

@InjectName("runflowlogService")
@IocBean(args =
{ "refer:dao" })
public class RunFlowLogService extends SkynetNameEntityService<RunFlowLog>
{
	public RunFlowLogService()
	{
		super();
	}

	public RunFlowLogService(Dao dao)
	{
		super(dao);
	}

	public RunFlowLogService(Dao dao, Class<RunFlowLog> entityType)
	{
		super(dao, entityType);
	}
}
