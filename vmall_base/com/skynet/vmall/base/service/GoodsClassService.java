package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsClass;

@InjectName("goodsclassService")
@IocBean(args =
{ "refer:dao" })
public class GoodsClassService extends SkynetNameEntityService<GoodsClass>
{
	public GoodsClassService()
	{
		super();
	}

	public GoodsClassService(Dao dao)
	{
		super(dao);
	}

	public GoodsClassService(Dao dao, Class<GoodsClass> entityType)
	{
		super(dao, entityType);
	}
}
