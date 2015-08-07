package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.GoodsPhoto;

@InjectName("goodsphotoService")
@IocBean(args =
{ "refer:dao" })
public class GoodsPhotoService extends SkynetNameEntityService<GoodsPhoto>
{
	public GoodsPhotoService()
	{
		super();
	}

	public GoodsPhotoService(Dao dao)
	{
		super(dao);
	}

	public GoodsPhotoService(Dao dao, Class<GoodsPhoto> entityType)
	{
		super(dao, entityType);
	}
}
