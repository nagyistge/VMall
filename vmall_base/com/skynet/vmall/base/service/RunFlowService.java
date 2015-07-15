package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.RunFlow;

@InjectName("runflowService")
@IocBean(args =
{ "refer:dao" })
public class RunFlowService extends SkynetNameEntityService<RunFlow>
{
	public RunFlowService()
	{
		super();
	}

	public RunFlowService(Dao dao)
	{
		super(dao);
	}

	public RunFlowService(Dao dao, Class<RunFlow> entityType)
	{
		super(dao, entityType);
	}
}
