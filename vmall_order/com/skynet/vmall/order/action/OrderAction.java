package com.skynet.vmall.order.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Xmls;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.WxApi;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;
import com.skynet.vmall.base.filter.LogFilter;
import com.skynet.vmall.order.service.OrderGoodsRebateService;
import com.skynet.vmall.order.service.OrderGoodsService;
import com.skynet.vmall.order.service.OrderService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/order/order")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/checksession.html" }) })
public class OrderAction extends BaseAction
{
	private Log log = Logs.get();
	
	@Inject
	WxApi myWxApi;	

	@Inject
	WXActionHelper myWxHelper;

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
		String id = (String) map.get("id");
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String keysignature = AuthorService.encode(AuthorService.gentext(AuthorService.getip(Mvcs.getReq()), userwxopenid));

		// 支付接口
		HttpServletRequest req = Mvcs.getReq();
		String info = (String) session.getAttribute(GlobalConstants.sys_wxinfo);
		if (!StringToolKit.isBlank(info))
		{
			NutMap mapwx = myWxHelper.wx_minfo(info, req);
			ro.put("jscfg", mapwx.get("jscfg"));
			ro.put("shareurl", mapwx.get("shareurl"));
			ro.put("openid", mapwx.get("openid"));
		}

		DynamicObject order = orderService.locate(id);
		List<DynamicObject> ordergoodses = ordergoodsService.list(new DynamicObject("orderid", id));

		ro.put("keysignature", keysignature);
		ro.put("order", order);
		ro.put("ordergoodses", ordergoodses);

		return ro;
	}

	@At("/listordergoods")
	@Ok("->:/page/order/order/listordergoods.ftl")
	public Map listordergoods(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id");
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		List<DynamicObject> ordergoodses = ordergoodsService.list(new DynamicObject("orderid", id));

		ro.put("ordergoodses", ordergoodses);

		return ro;
	}

	@At("/forward")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "订单付款" }), @By(type = CheckSession.class, args =
	{ "sys_login_token", "/checksession.html" }) })
	public Map forward(@Param("id") String id, @Param("keysignature") String keysignature) throws Exception
	{
		Map remap = new DynamicObject();
		try
		{
			HttpSession session = Mvcs.getHttpSession(true);
			DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
			String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
			String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);

			String decode = AuthorService.decode(keysignature);
			String ip = AuthorService.getip(Mvcs.getReq());

			remap = AuthorService.common_checksignature(decode, ip, userwxopenid);
			if (!("success".equals(remap.get("state"))))
			{
				return remap;
			}

			remap = orderService.forward(id);

			return remap;
		}
		catch (Exception e)
		{
			System.out.println(e);
			remap.put("state", "error");
			remap.put("message", "订单付款异常，请稍后再试。");
		}

		return remap;
	}

	// @At("/memberscore")
	// @Ok("->:/page/order/order/memberscore.ftl")
	// public Map memberscore(@Param("..") Map map) throws Exception
	// {
	// HttpSession session = Mvcs.getHttpSession(true);
	// DynamicObject login_token = (DynamicObject)
	// session.getAttribute(GlobalConstants.sys_login_token);
	// String userid =
	// login_token.getFormatAttr(GlobalConstants.sys_login_userid);
	// map.put("memberid", userid);
	// List<DynamicObject> scores = ordergoodsrebateService.memberscore(map);
	// BigDecimal sumscore = ordergoodsrebateService.sumscore(map);
	//
	// ro.put("sumscore", sumscore);
	// ro.put("scores", scores);
	// return ro;
	// }

	@At("/edittaker")
	@Ok("->:/page/order/order/edittaker.ftl")
	public Map edittaker(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		DynamicObject order = orderService.locate(id);
		ro.put("order", order);

		return ro;
	}

	@At("/savetaker")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map savetaker(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		Map remap = orderService.savetaker(map);
		return remap;
	}

	@At("/editmember")
	@Ok("->:/page/order/order/editmember.ftl")
	public Map editmember(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		DynamicObject order = orderService.locate(id);
		ro.put("order", order);
		return ro;
	}

	@At("/savemember")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map savemember(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		Map remap = orderService.savemember(map);
		return remap;
	}

	@At("/pay")
	@Ok("raw:json")
	public String pay(String orderno, String amt) throws Exception
	{
		amt = "1";
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		
		// myWxApi.getPrepayId(body, notifyurl, orderno, mchid, amt,
		// spbill_create_ip, openId, payKey)
		return myWxApi.getPrepayId("测试订单1", "http://www.rbtalking.com/vmall/order/order/paynotify.action", orderno, ApiConfigKit.apiConfig.getMchid(), amt, "10.0.0.1", userwxopenid, ApiConfigKit.apiConfig.getKey());

	}

	@At("/paynotify")
	@Ok("raw")
	public String paynotify(HttpServletRequest req)
	{

		String returnstr = "<xml><return_code><![CDATA[%s]]></return_code><return_msg><![CDATA[%s]]></return_msg></xml>";
		String returncode_success = "SUCCESS";
		String returncode_fail = "FAIL";

		String return_msg = "OK";

		try
		{

			InputStream inStream = req.getInputStream();
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1)
			{
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			String resultStr = new String(outSteam.toByteArray(), "utf-8");

			Document doc = Xmls.xml(new ByteArrayInputStream(resultStr.getBytes()));
			Element root = doc.getDocumentElement();
			Map wr = Xmls.asMap(root);
			log.debugf("get pay notify return object :\n %s", wr);
			String orderno = wr.get("out_trade_no").toString();

			/*
			 * 此处开始修改orderno的状态为已经支付
			 */

		}
		catch (Exception e)
		{
			// TODO: handle exception
			log.debug(e.toString());
			return String.format(returnstr, returncode_fail, e.toString());
		}

		return String.format(returnstr, returncode_success, return_msg);
	}

}
