package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.app.flow.pojo.BAct;
import com.skynet.app.flow.pojo.BFlow;
import com.skynet.app.flow.service.BActService;
import com.skynet.app.flow.service.BFlowService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.service.OrderService;
import com.skynet.vmall.order.service.AppOrderService;

@IocBean
@At("/order/order")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class OrderAction
{
	@Inject
	BFlowService bflowService;
	
	@Inject
	BActService bactService;	
	
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
		String flowstate = order.getFormatAttr("state");
		BFlow bflow = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_order_name));
		String bflowid = bflow.getId();
		BAct bact = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("cname", "=", flowstate));
		

		int sno = bact.getSno();
		String flownextstate = flowstate;
		String flowbackstate = flowstate;
		
		if(sno>1)
		{
			flowbackstate = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", (sno-1))).getCname();
		}
		
		if(!("结束".equals(bact.getCname())))
		{
			flownextstate = bactService.fetch(Cnd.where("bflowid", "=", bflowid).and("sno", "=", (sno+1))).getCname();
		}
		
		Map map = new DynamicObject();
		map.put("order", order);
		
		DynamicObject ro = new DynamicObject();
		
		set_author(map, ro);

		ro.put("order", order);
		ro.put("flowstate", flowstate);
		ro.put("flownextstate", flownextstate);
		ro.put("flowbackstate", flowbackstate);		
	
		return ro;
	}
	
	@At("/forward")
	@Ok("json")
	public Map forward(String id) throws Exception
	{	
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		DynamicObject form = new DynamicObject();
		form.setAttr("id", id);
		Map map = apporderService.foward(form, login_token);
		return map;
	}
	
	// 设置操作权限
	public void set_author(Map map, DynamicObject ro) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		map.put("login_token", login_token);
		
		boolean isread = false; // 是否可读
		boolean isedit = false; // 是否可修改
		boolean issave = issave(map); // 是否可保存

		boolean isforward = isforward(map); // 是否可转发
		boolean isbackward = isbackward(map); // 是否可回退
		
		ro.put("isread", isread);
		ro.put("isedit", isedit);
		ro.put("issave", issave);
		ro.put("isforward", isforward);
		ro.put("isbackward", isbackward);
		
	}
	
	// 保存功能权限
	public boolean issave(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject order = (DynamicObject) map.get("order");
		DynamicObject login_token =  (DynamicObject) map.get("login_token");
		
		// 订单处于下单状态
		if (!"下单".equals(order.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}
		
		return sign;
	}

	// 转发功能权限
	public boolean isforward(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject order = (DynamicObject) map.get("order");
		DynamicObject login_token =  (DynamicObject) map.get("login_token");

		// 评审处于评审状态；
		if ("结束".equals(order.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}
		
		return sign;
	}

	// 退回功能权限
	public boolean isbackward(Map map) throws Exception
	{
		boolean sign = true;

		DynamicObject order = (DynamicObject) map.get("order");
		DynamicObject login_token =  (DynamicObject) map.get("login_token");
		
		if ("下单".equals(order.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}

		if ("结束".equals(order.getFormatAttr("state")))
		{
			sign = false;
			return sign;
		}
		
		return sign;
	}


}
