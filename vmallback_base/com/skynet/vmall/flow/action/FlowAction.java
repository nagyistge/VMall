package com.skynet.vmall.flow.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.app.flow.service.AppFlowService;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@IocBean
@At("/flow/flow")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class FlowAction
{
	
	@Inject
	private AppFlowService appflowService;
	
	@At("/ajaxtrace")
	@Ok("json")
	public List<DynamicObject> ajaxtrace(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> flowlogs = appflowService.trace(map);
		return flowlogs;
	}



}
