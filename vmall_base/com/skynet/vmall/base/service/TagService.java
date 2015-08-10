package com.skynet.vmall.base.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.vmall.base.pojo.Tag;

@InjectName("tagService")
@IocBean(args =
{ "refer:dao" })
public class TagService extends SkynetNameEntityService<Tag>
{
	public TagService()
	{
		super();
	}

	public TagService(Dao dao)
	{
		super(dao);
	}

	public TagService(Dao dao, Class<Tag> entityType)
	{
		super(dao, entityType);
	}
}
