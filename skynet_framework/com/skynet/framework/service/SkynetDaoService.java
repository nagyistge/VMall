package com.skynet.framework.service;

import org.nutz.dao.Dao;
import org.nutz.service.Service;

import com.skynet.framework.dao.SkynetDao;

public abstract class SkynetDaoService extends Service
{
	Dao dao;

	public SkynetDaoService()
	{
		super();
	}

	public SkynetDaoService(Dao dao)
	{
		super();
		this.dao = dao;
	}

	public Dao dao()
	{
		return dao;
	}

	public void setDao(Dao dao)
	{
		this.dao = dao;
	}

	public SkynetDao sdao()
	{
		return (SkynetDao) dao();
	}

}
