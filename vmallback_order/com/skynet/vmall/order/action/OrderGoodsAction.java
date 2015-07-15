package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.service.OrderService;
import com.skynet.vmall.order.service.AppOrderGoodsService;

@IocBean
@At("/order/ordergoods")
public class OrderGoodsAction
{
	@Inject
	private OrderService orderService;
	
	@Inject
	private AppOrderGoodsService appordergoodsService;
	
	@At("/ajaxlist")
	@Ok("json")
	public List ajaxlist(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> orders = appordergoodsService.list(map);
	
		return orders;
	}


}
