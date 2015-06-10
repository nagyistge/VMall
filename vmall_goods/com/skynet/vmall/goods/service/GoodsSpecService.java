package com.skynet.vmall.goods.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsSpec;

@InjectName("goodsspecService")
@IocBean(args = { "refer:dao" }) 
public class GoodsSpecService extends SkynetNameEntityService<GoodsSpec>
{
	public GoodsSpecService()
	{
		super();
	}
	
	public GoodsSpecService(Dao dao)
	{
		super(dao);
	}	
	
	public GoodsSpecService(Dao dao, Class<GoodsSpec> entityType)
	{
		super(dao, entityType);
	}
}
