package com.skynet.vmall.order.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Xmls;
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
import com.skynet.app.dictionary.service.DictionaryService;
import com.skynet.app.organ.pojo.User;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.common.generator.RandomGenerator;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.author.AuthorService;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.filter.LogFilter;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.service.MemberService;
import com.skynet.vmall.base.service.OrderGoodsRebateService;
import com.skynet.vmall.base.service.OrderGoodsService;
import com.skynet.vmall.base.service.OrderService;
import com.skynet.vmall.order.service.AppOrderGoodsRebateService;
import com.skynet.vmall.order.service.AppOrderGoodsService;
import com.skynet.vmall.order.service.AppOrderService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/order/order")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/checksession.html" }) })
public class OrderAction
{
	private Log log = Logs.get();

	@Inject
	WxApi myWxApi;

	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private DictionaryService dictionaryService;	
	
	@Inject
	MemberService memberService;
	
	@Inject
	private OrderService orderService;

	@Inject
	private OrderGoodsService ordergoodsService;

	@Inject
	private OrderGoodsRebateService ordergoodsrebateService;

	@Inject
	private AppOrderService apporderService;

	@Inject
	private AppOrderGoodsService appordergoodsService;

	@Inject
	private AppOrderGoodsRebateService appordergoodsrebateService;

	@At("/index")
	@Ok("->:/page/order/order/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		DynamicObject ro = new DynamicObject();
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

		DynamicObject ro = new DynamicObject();
		
		// 支付接口
		HttpServletRequest req = Mvcs.getReq();
		
		Map wxinfo = myWxHelper.wx_jsconfig(myWxHelper.wx_uri(req));
		ro.put("jscfg", wxinfo);
		
		String jsdebug = StringToolKit.formatText(dictionaryService.locateBy(Cnd.where("dkey", "=", "app.system.weixin.jsdebug")).getFormatAttr("dvalue"),"false");		
		ro.put("jsdebug", jsdebug);
		
		DynamicObject order = orderService.locate(id);
		List<DynamicObject> ordergoodses = appordergoodsService.list(new DynamicObject("orderid", id));

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

		DynamicObject order = orderService.locate(id);
		List<DynamicObject> ordergoodses = appordergoodsService.list(new DynamicObject("orderid", id));

		DynamicObject ro = new DynamicObject();
		
		ro.put("order", order);
		ro.put("ordergoodses", ordergoodses);

		return ro;
	}

	@At("/list")
	@Ok("->:/page/order/order/list.ftl")
	public Map list(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);

		map.put("_page", Types.parseInt(page, 1));
		map.put("_pagesize", Types.parseInt(pagesize, VMallConstants.pagesize));
		map.put("memberid", userid);

		List<DynamicObject> orders = apporderService.browse(map);

		DynamicObject ro = new DynamicObject();

		ro.put("orders", orders);

		ro.put("_page", map.get("_page"));
		ro.put("_pagesize", map.get("_pagesize"));
		ro.put("_maxpage", map.get("_maxpage"));
		ro.put("_startpage", map.get("_startpage"));
		ro.put("_endpage", map.get("_endpage"));

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

			DynamicObject form = new DynamicObject();
			form.put("id", id);
			// 后继增加需要的参数及值
			remap = apporderService.forward(form, login_token);

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

	@At("/edittaker")
	@Ok("->:/page/order/order/edittaker.ftl")
	public Map edittaker(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		DynamicObject order = orderService.locate(id);
		
		DynamicObject ro = new DynamicObject();
		ro.put("order", order);

		return ro;
	}

	@At("/savetaker")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map savetaker(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		Map remap = apporderService.savetaker(map);
		return remap;
	}

	@At("/editpayer")
	@Ok("->:/page/order/order/editpayer.ftl")
	public Map editpayer(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		DynamicObject order = orderService.locate(id);
		
		DynamicObject ro = new DynamicObject();
		ro.put("order", order);
		return ro;
	}

	@At("/savepayer")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map savepayer(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); // 订单编号
		Map remap = apporderService.savepayer(map);
		return remap;
	}

	@At("/pay")
	// @Ok("raw:json")
	@Ok("json")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "订单付款" }) })
	public Map pay(String orderno, String amt) throws Exception
	{
		amt = "1";
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		DynamicObject order = orderService.locateBy(Cnd.where("cno", "=", orderno));
		if (StringToolKit.isBlank(order.getFormatAttr("id")))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "亲，没有找到这个订单，无法付款。");
			return remap;
		}

		if (!"下单".equals(order.getFormatAttr("state")))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "亲，这个订单不能再拍了，看看是不是已经拍过了。");
			return remap;
		}
		
		if (!"未支付".equals(order.getFormatAttr("paystate")))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "亲，这个订单不能再拍了，看看是不是已经付款了。");
			return remap;
		}		

		String orderid = orderService.locateBy(Cnd.where("cno", "=", orderno)).getFormatAttr("id");
		// 更新订单商品价格和金额（系统单位为元，需要乘以100转换为分）
		Map paymap = new DynamicObject();
		paymap = apporderService.pay(orderid, login_token);

		if ("error".equals((String) paymap.get("state")))
		{
			return paymap;
		}

		amt = String.valueOf(((BigDecimal) paymap.get("amt")).multiply(new BigDecimal(1)).intValue());
		amt = "1";

		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String url = "http://" + VMallConstants.svr_domianname + "/" + VMallConstants.app_webcontext + "/order/order/paynotify.action";

		String res = myWxApi.getPrepayId(orderno, url, orderno, ApiConfigKit.apiConfig.getMchid(), amt, "10.0.0.1", userwxopenid, ApiConfigKit.apiConfig.getKey());

		Map remap = new DynamicObject();
		remap.put("state", "success");
		remap.put("res", res);

		return remap;

	}

	@At("/paynotify")
	@Ok("raw")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "订单付款回执通知" }) })
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

			String orderno = (String) wr.get("out_trade_no");
			String deviceinfo = (String) wr.get("device_info"); // 微信支付分配的终端设备号

			String wxtransactionid = (String) wr.get("transaction_id");
			String tradetype = (String) wr.get("trade_type"); // 交易类型
			String banktype = (String) wr.get("bank_type"); // 付款银行
			String totalfee = (String) wr.get("total_fee");
			String issubscribe = (String) wr.get("is_subscribe"); // 是否关注公众号
			String openid = (String) wr.get("openid");
			String timeend = (String) wr.get("time_end"); // 交易完成时间

			log.debugf("orderno :\n %s", orderno);

			Order order = orderService.fetch(Cnd.where("cno", "=", orderno));
			String id = order.getId();
			// 检查是否已经成功回执过
			if (StringToolKit.isBlank(order.getThirdpaytradeno())&&StringToolKit.isBlank(order.getWxpaytimeend()))
			{
				DynamicObject form = new DynamicObject();
				form.setAttr("id", id);
				form.setAttr("wxpayorderno", orderno);
				form.setAttr("wxpaydeviceinfo", deviceinfo);
				form.setAttr("wxpaytransactionid", wxtransactionid);
				form.setAttr("wxpaytradetype", tradetype);
				form.setAttr("wxpaybanktype", banktype);
				form.setAttr("wxpaytotalfee", totalfee);
				form.setAttr("wxpayissubscribe", issubscribe);
				form.setAttr("wxpaytimeend", timeend);
				form.setAttr("wxpayopenid", openid);

				// 该请求为微信支付平台发出，用户信息现取，不能从会话取出。
				DynamicObject login_token = new DynamicObject();

				User user = orderService.sdao().fetch(User.class, Cnd.where("wxopenid", "=", openid));
				login_token.setAttr(GlobalConstants.sys_login_user, user.getLoginname());
				login_token.setAttr(GlobalConstants.sys_login_username, user.getCname());
				login_token.setAttr(GlobalConstants.sys_login_userid, user.getId());
				login_token.setAttr(GlobalConstants.sys_login_userwxopenid, user.getWxopenid());
				apporderService.paynotify(form, login_token);
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			log.debug(e.toString());
			return String.format(returnstr, returncode_fail, e.toString());
		}

		log.debug(String.format(returnstr, returncode_success, return_msg));

		return String.format(returnstr, returncode_success, return_msg);
	}
	
	@At("/paynotifytest")
	@Ok("raw")
	@Filters(
	{ @By(type = LogFilter.class, args =
	{ "测试订单付款回执通知" }) })
	public String paynotifytest(String orderno)
	{

		try
		{
			Map wr = new HashMap();
			wr.put("out_trade_no", orderno);
			wr.put("transaction_id", "TEST"+RandomGenerator.getValue(8));
			wr.put("total_fee", "0");
			
			log.debugf("get pay notify return object :\n %s", wr);

			// String orderno = (String) wr.get("out_trade_no");
			String deviceinfo = (String) wr.get("device_info"); // 微信支付分配的终端设备号

			String wxtransactionid = (String) wr.get("transaction_id");
			String tradetype = (String) wr.get("trade_type"); // 交易类型
			String banktype = (String) wr.get("bank_type"); // 付款银行
			String totalfee = (String) wr.get("total_fee");
			String issubscribe = (String) wr.get("is_subscribe"); // 是否关注公众号
			// String openid = (String) wr.get("openid");
			String timeend = (String) wr.get("time_end"); // 交易完成时间

			log.debugf("orderno :\n %s", orderno);

			Order order = orderService.fetch(Cnd.where("cno", "=", orderno));
			String openid = memberService.fetch(order.getMemberid()).getWxopenid();
			
			
			String id = order.getId();
			// 检查是否已经成功回执过
			if (StringToolKit.isBlank(order.getThirdpaytradeno())&&StringToolKit.isBlank(order.getWxpaytimeend()))
			{
				DynamicObject form = new DynamicObject();
				form.setAttr("id", id);
				form.setAttr("wxpayorderno", orderno);
				form.setAttr("wxpaydeviceinfo", deviceinfo);
				form.setAttr("wxpaytransactionid", wxtransactionid);
				form.setAttr("wxpaytradetype", tradetype);
				form.setAttr("wxpaybanktype", banktype);
				form.setAttr("wxpaytotalfee", totalfee);
				form.setAttr("wxpayissubscribe", issubscribe);
				form.setAttr("wxpaytimeend", timeend);
				form.setAttr("wxpayopenid", openid);

				// 该请求为微信支付平台发出，用户信息现取，不能从会话取出。
				DynamicObject login_token = new DynamicObject();

				User user = orderService.sdao().fetch(User.class, Cnd.where("wxopenid", "=", openid));
				login_token.setAttr(GlobalConstants.sys_login_user, user.getLoginname());
				login_token.setAttr(GlobalConstants.sys_login_username, user.getCname());
				login_token.setAttr(GlobalConstants.sys_login_userid, user.getId());
				login_token.setAttr(GlobalConstants.sys_login_userwxopenid, user.getWxopenid());
				apporderService.paynotify(form, login_token);
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			log.debug(e.toString());
			return "ERROR";
		}

		return "SUCCESS";
	}	

	@At("/delete")
	@Ok("json")
	public Map delfromcart(String id) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		Map remap = apporderService.deleteorder(id, login_token);
		return remap;
	}

	@At("/inputtakeover")
	@Ok("->:/page/order/order/inputtakeover.ftl")
	public Map inputtakeover(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id"); //
		DynamicObject order = orderService.locate(id);
		
		DynamicObject ro = new DynamicObject();
		ro.put("id", id);
		ro.put("order", order);
		return ro;
	}

	@At("/inputgoodstakeover")
	@Ok("->:/page/order/order/inputgoodstakeover.ftl")
	public Map inputgoodstakeover(@Param("..") Map map) throws Exception
	{
		String ordergoodsid = (String) map.get("ordergoodsid"); //
		DynamicObject ordergoods = ordergoodsService.locate(ordergoodsid);
		String id = ordergoods.getFormatAttr("orderid");
		DynamicObject order = orderService.locate(id);

		DynamicObject ro = new DynamicObject();
		ro.put("id", id);
		ro.put("ordergoodsid", ordergoodsid);
		ro.put("order", order);
		ro.put("ordergoods", ordergoods);
		return ro;
	}

	@At("/savetakeover")
	@Ok("json")
	public Map savetakeover(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		Map remap = apporderService.savetakeover(map, login_token);
		return remap;
	}

	@At("/savealltakeover")
	@Ok("json")
	public Map savealltakeover(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		Map remap = apporderService.savealltakeover(map, login_token);
		return remap;
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(String.valueOf(new BigDecimal("3.5").multiply(new BigDecimal(100)).intValue()));
	}

}
