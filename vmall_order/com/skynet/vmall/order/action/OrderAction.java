package com.skynet.vmall.order.action;

import java.util.List;
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
import com.skynet.vmall.order.service.OrderGoodsRebateService;
import com.skynet.vmall.order.service.OrderService;

@IocBean
@At("/order/order")
public class OrderAction extends BaseAction
{
	@Inject
	private OrderService orderService;
	
	@Inject
	private OrderGoodsRebateService ordergoodsrebateService;	

	@At("/index")
	@Ok("->:/page/order/order/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		return ro;
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
