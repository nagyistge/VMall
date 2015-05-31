package com.skynet.vmall.system.action;

import java.util.Map;

import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.skynet.app.organ.service.OrganService;
import com.skynet.framework.action.BaseAction;

@IocBean
@At("/system/system")
public class SystemAction extends BaseAction
{
	@Inject
	private OrganService organService;
	
	@At("/inittable")
	@Ok("json")
	public Map inittable() throws Exception
	{
		// 初始化表结构
		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.organ.pojo", true);
		Daos.createTablesInPackage(organService.dao(), "com.skynet.vmall.base.pojo", true);
		Daos.createTablesInPackage(organService.dao(), "com.skynet.app.dictionary.pojo", true);
		return ro;
	}

	public OrganService getOrganService()
	{
		return organService;
	}

	public void setOrganService(OrganService organService)
	{
		this.organService = organService;
	}
	
	
}
