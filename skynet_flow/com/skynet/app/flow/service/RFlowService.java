package com.skynet.app.flow.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.RFlow;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("rflowService")
@IocBean(args =
{ "refer:dao" })
public class RFlowService extends SkynetNameEntityService<RFlow>
{
	public RFlowService()
	{
		super();
	}

	public RFlowService(Dao dao)
	{
		super(dao);
	}

	public RFlowService(Dao dao, Class<RFlow> entityType)
	{
		super(dao, entityType);
	}
}
