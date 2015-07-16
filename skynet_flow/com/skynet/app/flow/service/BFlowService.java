package com.skynet.app.flow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.BFlow;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("bflowService")
@IocBean(args =
{ "refer:dao" })
public class BFlowService extends SkynetNameEntityService<BFlow>
{
	public BFlowService()
	{
		super();
	}

	public BFlowService(Dao dao)
	{
		super(dao);
	}

	public BFlowService(Dao dao, Class<BFlow> entityType)
	{
		super(dao, entityType);
	}
}
