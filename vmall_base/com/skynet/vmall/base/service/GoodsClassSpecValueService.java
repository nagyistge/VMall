package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsClassSpecValue;

@InjectName("goodsclassspecvalueService")
@IocBean(args =
{ "refer:dao" })
public class GoodsClassSpecValueService extends SkynetNameEntityService<GoodsClassSpecValue>
{
	public GoodsClassSpecValueService()
	{
		super();
	}

	public GoodsClassSpecValueService(Dao dao)
	{
		super(dao);
	}

	public GoodsClassSpecValueService(Dao dao, Class<GoodsClassSpecValue> entityType)
	{
		super(dao, entityType);
	}
}
