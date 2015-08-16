package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Dealer;
import com.skynet.vmall.base.pojo.Supplier;

@InjectName("supplierService")
@IocBean(args =
{ "refer:dao" })
public class SupplierService extends SkynetNameEntityService<Supplier>
{
	public SupplierService()
	{
		super();
	}

	public SupplierService(Dao dao)
	{
		super(dao);
	}

	public SupplierService(Dao dao, Class<Supplier> entityType)
	{
		super(dao, entityType);
	}
}
