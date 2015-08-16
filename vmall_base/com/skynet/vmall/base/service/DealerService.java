package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Dealer;

@InjectName("dealerService")
@IocBean(args =
{ "refer:dao" })
public class DealerService extends SkynetNameEntityService<Dealer>
{
	public DealerService()
	{
		super();
	}

	public DealerService(Dao dao)
	{
		super(dao);
	}

	public DealerService(Dao dao, Class<Dealer> entityType)
	{
		super(dao, entityType);
	}
}
