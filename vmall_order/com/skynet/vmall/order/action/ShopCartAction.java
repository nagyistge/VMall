package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.skynet.vmall.order.service.ShopCartService;

@IocBean
@At("/order/shopcart")
public class ShopCartAction extends BaseAction
{
	@Inject
	private ShopCartService shopcartService;

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
	
	@At("/settlement")
	@AdaptBy(type=JsonAdaptor.class)
	@Ok("json")
	public Map settlement(@Param("ids") List ids) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		DynamicObject form = new DynamicObject();
		form.setObj("ids", ids);
		Map remap = shopcartService.settlement(form, login_token);
		return remap;
	}
	
	
}
