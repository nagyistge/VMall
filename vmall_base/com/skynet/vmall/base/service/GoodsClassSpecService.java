package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsClassSpec;

@InjectName("goodsclassspecService")
@IocBean(args =
{ "refer:dao" })
public class GoodsClassSpecService extends SkynetNameEntityService<GoodsClassSpec>
{
	public GoodsClassSpecService()
	{
		super();
	}

	public GoodsClassSpecService(Dao dao)
	{
		super(dao);
	}

	public GoodsClassSpecService(Dao dao, Class<GoodsClassSpec> entityType)
	{
		super(dao, entityType);
	}
}
