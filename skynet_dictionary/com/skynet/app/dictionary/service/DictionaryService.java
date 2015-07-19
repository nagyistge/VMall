package com.skynet.app.dictionary.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.dictionary.pojo.Dictionary;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("dictionaryService")
@IocBean(args =
{ "refer:dao" })
public class DictionaryService extends SkynetNameEntityService<Dictionary>
{
	public DictionaryService()
	{
		super();
	}

	public DictionaryService(Dao dao)
	{
		super(dao);
	}

	public DictionaryService(Dao dao, Class<Dictionary> entityType)
	{
		super(dao, entityType);
	}
}
