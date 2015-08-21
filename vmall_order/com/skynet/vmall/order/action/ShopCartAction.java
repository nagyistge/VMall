package com.skynet.vmall.order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;
import com.skynet.vmall.base.filter.LogFilter;
import com.skynet.vmall.base.service.ShopCartGoodsService;
import com.skynet.vmall.base.service.ShopCartService;
import com.skynet.vmall.order.service.AppShopCartService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/order/shopcart")
@Filters({@By(type=CheckSession.class, args={"sys_login_token", "/checksession.html"})})	
public class ShopCartAction
{
	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private ShopCartService shopcartService;	
	
	@Inject
	private AppShopCartService appshopcartService;
	
	@Inject
	private ShopCartGoodsService shopcartgoodsService;	

	@At("/index")
	@Ok("->:/page/order/shopcart/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String keysignature = AuthorService.encode(AuthorService.gentext(AuthorService.getip(Mvcs.getReq()), userwxopenid));
		
		map.put("memberid", userid);
		List<DynamicObject> shopcartgoods = appshopcartService.browse(map);

		DynamicObject ro = new DynamicObject();
		ro.put("keysignature", keysignature);		
		ro.put("shopcartgoods", shopcartgoods);
		return ro;
	}

	@At("/addtocart")
	@Ok("json")
	public Map addtocart(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		Map remap = appshopcartService.addtocart(new DynamicObject(map), login_token);
		return remap;
	}

	@At("/delfromcart")
	@Ok("json")
	public Map delfromcart(String id) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		Map remap = appshopcartService.delfromcart(id, login_token);
		return remap;
	}

	// 填写订单
	@At("/placeorder")
	@AdaptBy(type = JsonAdaptor.class)	
	@Ok("json")
	@Filters({@By(type=LogFilter.class, args={"订单下单"})})	
	public Map placeorder(@Param("..") Map map) throws Exception
	{
		List<String> ids = (List<String>)map.get("ids");
		List<String> numses = (List<String>)map.get("numses");
		String keysignature = (String)map.get("keysignature");

		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

		DynamicObject form = new DynamicObject();
		form.setObj("ids", ids);	
		form.setObj("numses", numses);
		
		Map remap = new DynamicObject();
		try
		{
//			String decode = AuthorService.decode(keysignature);
//			String ip = AuthorService.getip(Mvcs.getReq());			
//			remap = AuthorService.common_checksignature(decode, ip, userwxopenid);
//			if(!("success".equals(remap.get("state"))))
//			{
//				return remap;
//			}
			
			remap = appshopcartService.placeorder(form, login_token);
			
			return remap;
		}
		catch (Exception e)
		{
			System.out.println(e);
			remap.put("state", "error");
			remap.put("message", "申请提现异常，请稍后再试。");
		}
		
		return remap;
	}
	
	// 抢购订单
	@At("/rushorder")
	@Ok("json")
	@Filters({@By(type=LogFilter.class, args={"抢购下单"})})	
	public Map rushorder(String info) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		NutMap mapwx = myWxHelper.wx_minfo(info, req);
		String goodsid = (String)mapwx.get("goodsid");// 商品标识 		
		
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

		DynamicObject form = new DynamicObject();
		form.setObj("ids", new String[]{goodsid});	
		form.setObj("numses", new String[]{"1"});
		
		Map remap = new DynamicObject();
		try
		{
			remap = appshopcartService.placeorder(form, login_token);
			
			return remap;
		}
		catch (Exception e)
		{
			System.out.println(e);
			remap.put("state", "error");
			remap.put("message", "申请提现异常，请稍后再试。");
		}
		
		return remap;
	}

}
