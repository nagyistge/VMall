package com.skynet.framework.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;

import com.skynet.framework.dao.SkynetDao;

public class SkynetDaoService
{
	@Inject
	Dao dao;

	public Dao dao()
	{
		return dao;
	}

	public void setDao(Dao dao)
	{
		this.dao = dao;
	}
	
	public SkynetDao sdao() {
		return (SkynetDao) dao();
	}
	
	
	
}
