package com.skynet.vmall.order.action;

import java.math.BigDecimal;
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
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.order.service.OrderGoodsRebateService;
import com.skynet.vmall.order.service.OrderGoodsService;
import com.skynet.vmall.order.service.OrderService;

@IocBean
@At("/order/order")
@Filters({@By(type=CheckSession.class, args={"sys_login_token", "/checksession.html"})})	
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
		List<DynamicObject> ordergoodses = ordergoodsService.list(new DynamicObject("orderid", id));
		
		ro.put("order", order);
		ro.put("ordergoodses", ordergoodses);

		return ro;
	}
	
	@At("/listordergoods")
	@Ok("->:/page/order/order/listordergoods.ftl")
	public Map listordergoods(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id");
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		List<DynamicObject> ordergoodses = ordergoodsService.list(new DynamicObject("orderid", id));
		
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
		BigDecimal sumscore = ordergoodsrebateService.sumscore(map);
	
		ro.put("sumscore", sumscore);
		ro.put("scores", scores);
		return ro;
	}
	
	
	@At("/edittaker")
	@Ok("->:/page/order/order/edittaker.ftl")
	public Map edittaker(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id"); //订单编号
		DynamicObject order = orderService.locate(id);
		ro.put("order", order);
		
		return ro;
	}
	
	@At("/savetaker")
	@AdaptBy(type = JsonAdaptor.class)	
	@Ok("json")
	public Map savetaker(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id"); //订单编号
		Map remap = orderService.savetaker(map);
		return remap;
	}
	
	@At("/editmember")
	@Ok("->:/page/order/order/editmember.ftl")
	public Map editmember(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id"); //订单编号
		DynamicObject order = orderService.locate(id);
		ro.put("order", order);
		return ro;
	}
	
	@At("/savemember")
	@AdaptBy(type = JsonAdaptor.class)	
	@Ok("json")
	public Map savemember(@Param("..") Map map) throws Exception
	{
		String id = (String)map.get("id"); //订单编号
		Map remap = orderService.savemember(map);
		return remap;
	}
	
	
}
