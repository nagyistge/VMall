package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.service.OrderService;
import com.skynet.vmall.order.service.AppOrderService;

@IocBean
@At("/order/order")
public class OrderAction
{
	@Inject
	private OrderService orderService;
	
	@Inject
	private AppOrderService apporderService;
	
	@At("/browse")
	@Ok("->:/page/order/order/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Types.parseInt(page, 1));
		map.put("_pagesize", Types.parseInt(pagesize, VMallConstants.pagesize));

		List<DynamicObject> orders = apporderService.browse(map);
	
		DynamicObject ro = new DynamicObject();

		ro.put("orders", orders);
		ro.setAttr("state", (String)map.get("state"));
		
		ro.put("_page", map.get("_page"));
		ro.put("_pagesize", map.get("_pagesize"));
		ro.put("_maxpage", map.get("_maxpage"));
		ro.put("_startpage", map.get("_startpage"));
		ro.put("_endpage", map.get("_endpage"));

		return ro;
	}
	
	
	@At("/locate")
	@Ok("->:/page/order/order/locate.ftl")
	public Map locate(String id) throws Exception
	{
		DynamicObject order = orderService.locate(id);
	
		DynamicObject ro = new DynamicObject();

		ro.put("order", order);

		return ro;
	}

}
