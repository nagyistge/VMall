package com.skynet.vmall.order.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.order.service.OrderService;

@IocBean
@At("/order/order")
public class OrderAction extends BaseAction
{
	@Inject
	private OrderService orderService;

	@At("/index")
	@Ok("->:/page/order/order/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		return ro;
	}
	
	
}
