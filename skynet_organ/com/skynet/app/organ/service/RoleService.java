package com.skynet.app.organ.service;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.pojo.Role;
import com.skynet.framework.service.SkynetNameEntityService;

@InjectName("roleService")
@IocBean(args = { "refer:dao" }) 
public class RoleService extends SkynetNameEntityService<Role>
{
	public RoleService()
	{
		super();
	}
	
	public RoleService(Dao dao)
	{
		super(dao);
	}	
	
	public RoleService(Dao dao, Class<Role> entityType)
	{
		super(dao, entityType);
	}
}
