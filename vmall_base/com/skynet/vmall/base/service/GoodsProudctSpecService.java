package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsProductSpec;

@InjectName("goodsproductspecService")
@IocBean(args =
{ "refer:dao" })
public class GoodsProudctSpecService extends SkynetNameEntityService<GoodsProductSpec>
{
	public GoodsProudctSpecService()
	{
		super();
	}

	public GoodsProudctSpecService(Dao dao)
	{
		super(dao);
	}

	public GoodsProudctSpecService(Dao dao, Class<GoodsProductSpec> entityType)
	{
		super(dao, entityType);
	}
}
