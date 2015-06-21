package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.order.service.ShopCartGoodsService;
import com.skynet.vmall.order.service.ShopCartService;

@IocBean
@At("/order/shopcart")
@Filters({@By(type=CheckSession.class, args={"sys_login_token", "/checksession.html"})})	
public class ShopCartAction extends BaseAction
{
	@Inject
	private ShopCartService shopcartService;
	
	@Inject
	private ShopCartGoodsService shopcartgoodsService;	

	@At("/index")
	@Ok("->:/page/order/shopcart/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		map.put("memberid", userid);
		List<DynamicObject> shopcartgoods = shopcartService.browse(map);
		ro.put("shopcartgoods", shopcartgoods);
		return ro;
	}

	@At("/addtocart")
	@Ok("json")
	public Map addtocart(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		Map remap = shopcartService.addtocart(new DynamicObject(map), login_token);
		return remap;
	}

	@At("/delfromcart")
	@Ok("json")
	public Map delfromcart(@Param("..") Map map) throws Exception
	{
		return ro;
	}

	// 填写订单
	@At("/placeorder")
	@Ok(">>:/order/order/look.action?id=${obj.id}")
	public Map placeorder(@Param("id") List<String> ids, @Param("nums") List<String> numses) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		DynamicObject form = new DynamicObject();
		form.setObj("ids", ids);	
		form.setObj("numses", numses);
		String orderid = shopcartService.placeorder(form, login_token);
		ro.put("id", orderid);
		return ro;
	}

}
