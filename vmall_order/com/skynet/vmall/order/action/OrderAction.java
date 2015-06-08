package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.order.service.OrderGoodsRebateService;
import com.skynet.vmall.order.service.OrderGoodsService;
import com.skynet.vmall.order.service.OrderService;

@IocBean
@At("/order/order")
public class OrderAction extends BaseAction
{
	@Inject
	private OrderService orderService;

	@Inject
	private OrderGoodsService ordergoodsService;

	@Inject
	private OrderGoodsRebateService ordergoodsrebateService;

	@At("/index")
	@Ok("->:/page/order/order/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		return ro;
	}

	@At("/look")
	@Ok("->:/page/order/order/look.ftl")
	public Map look(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id");
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		DynamicObject order = orderService.locate(id);
		List<DynamicObject> ordergoodses = ordergoodsService.findByCond(Cnd.where("orderid", "=", id));
		
		ro.put("order", order);
		ro.put("ordergoodses", ordergoodses);

		return ro;
	}
	
	@At("/forward")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map forward(@Param("id") String id) throws Exception
	{
		Map map = orderService.forward(id);
		return map;
	}	

	@At("/memberscore")
	@Ok("->:/page/order/order/memberscore.ftl")
	public Map memberscore(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> scores = ordergoodsrebateService.memberscore(map);
		ro.put("scores", scores);
		return ro;
	}
	
}
