package com.blue.wxmp.sdk.api;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.nutz.castor.Castors;
import org.nutz.http.Request;
import org.nutz.http.Request.METHOD;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Xmls;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.bean.WxPrePay;
import com.blue.wxmp.sdk.util.WxUtils;

public abstract class AbstractWxApi implements WxApi
{
	protected final static Log log = Logs.get();

	private static String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send";

	private static String callUnifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	private static String sendCustomeUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	
	public static final String authorUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	@Override
	public String getCompleteUri(HttpServletRequest req)
	{
		// TODO Auto-generated method stub
		String uri = Mvcs.getRequestPathObject(req).getUrl() + (req.getQueryString() != null ? "?" + req.getQueryString() : "");

		log.debugf("getCompleteUri:%s", ApiConfigKit.apiConfig.getServercontext() + uri);

		return ApiConfigKit.apiConfig.getServercontext() + uri;
	}

	@Override
	public String getAccessToken()
	{
		// TODO Auto-generated method stub
		return AccessTokenApi.getAccessToken().getAccessToken();
	}

	public NutMap genJsSDKConfig(String url, String... jsApiList)
	{
		String jt = JsapiTicketApi.getJsapiTicket().getJsapiTicket();
		long timestamp = System.currentTimeMillis() / 1000;
		String nonceStr = R.UU64();

		String str = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%d&url=%s", jt, nonceStr, timestamp, url);

		log.debugf("--------------> %s", str);

		String signature = Lang.sha1(str);
		Logs.get().debugf("--------------> signature:%s", signature);
		NutMap map = new NutMap();
		map.put("appId", ApiConfigKit.apiConfig.getAppId());
		map.put("timestamp", timestamp);
		map.put("nonceStr", nonceStr);
		map.put("signature", signature);
		map.put("jsApiList", jsApiList);
		return map;
	}

	/**
	 * 发送模板消息
	 */
	@Override
	public NutMap sendTemplateMsg(NutMap body)
	{

		return postJson(sendTemplateMsgUrl, body);
	}

	protected NutMap postJson(String uri, NutMap body)
	{
		return call(uri, METHOD.POST, Json.toJson(body));
	}

	protected NutMap call(String URL, METHOD method, String body)
	{
		String token = getAccessToken();
		if (URL.contains("?"))
		{
			URL += "&access_token=" + token;
		}
		else
		{
			URL += "?access_token=" + token;
		}
		if (log.isInfoEnabled())
		{
			log.info("wxapi call: " + URL);
			log.debug(body);

		}

		Request req = Request.create(URL, method);
		if (body != null)
			req.setData(body);
		Response resp = Sender.create(req).send();
		if (!resp.isOK())
			throw new IllegalArgumentException("resp code=" + resp.getStatus());
		return Json.fromJson(NutMap.class, resp.getReader());
	}

	@Override
	public String getPrepayId(String body, String notifyurl, String orderno, String mchid, String amt, String spbill_create_ip, String openId, String payKey)
	{
		String nonceStr = R.UU64();
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("appid", ApiConfigKit.apiConfig.getAppId());
		signParams.put("body", body); // 商品描述
		signParams.put("notify_url", notifyurl); // 通知地址
		signParams.put("out_trade_no", orderno); // 商户订单号
		signParams.put("mch_id", mchid); // 设置商户号
		signParams.put("total_fee", amt); // 商品总金额,以分为单位
		signParams.put("spbill_create_ip", spbill_create_ip); // 订单生成机器IP，指用户浏览器端IP
		signParams.put("trade_type", "JSAPI");
		signParams.put("nonce_str", nonceStr);
		signParams.put("openid", openId);
		String sign = "";
		try
		{
			sign = WxUtils.createSign(signParams, payKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		// 增加非参与签名的额外参数
		// asUnifiedorder(signParams);

		signParams.put("sign", sign);

		Request req = Request.create(callUnifiedOrderUrl, METHOD.POST);
		if (body != null)
			req.setData(asUnifiedorderXML(signParams));
		Response resp = Sender.create(req).send();
		if (!resp.isOK())
			throw new IllegalArgumentException("resp code=" + resp.getStatus());
		String xmlstr = resp.getContent();

		Document doc = Xmls.xml(new ByteArrayInputStream(xmlstr.getBytes()));
		Element root = doc.getDocumentElement();
		Map wr = Xmls.asMap(root);

		SortedMap<String, String> sa = new TreeMap<String, String>();

		sa.put("appId", signParams.get("appid"));
		sa.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
		sa.put("nonceStr", signParams.get("nonce_str"));
		sa.put("package", String.format("prepay_id=%s", wr.get("prepay_id")));
		sa.put("signType", "MD5");

		String paySign = WxUtils.createSign(sa, payKey);
		sa.put("paySign", paySign);

		wr.put("sa", sa);

		/*
		 * { "return_code" :"SUCCESS", "return_msg" :"OK", "appid"
		 * :"wxd986013eeb54f390", "mch_id" :"1247511701", "nonce_str"
		 * :"mPhw0Oa85VYtcGsm", "sign" :"1F4E5F28D879C9DD9B3AE2ECFFE14736",
		 * "result_code" :"SUCCESS", "prepay_id"
		 * :"wx20150618143949424399f9890214617868", "trade_type" :"JSAPI" }
		 */

		// string paysign = paysignReqHandler.CreateMd5Sign("key",Key);
		//
		// paysignReqHandler.SetParameter("", paysign);

		log.debugf("preycall result= \n %s", Json.toJson(wr));

		return Json.toJson(wr);

		// String prePayId = WxPayHelper.getPrePayId(reqPrePayUrl,
		// signParams);// 预支付订单号
	}

	private static WxPrePay asUnifiedorder(SortedMap<String, String> signParams)
	{
		return new WxPrePay(signParams.get("appid"), signParams.get("body"), signParams.get("mch_id"), signParams.get("nonce_str"), signParams.get("notify_url"), signParams.get("openid"), signParams.get("out_trade_no"),
				signParams.get("spbill_create_ip"), signParams.get("total_fee"), signParams.get("trade_type"), signParams.get("sign"));

	}

	private static String asUnifiedorderXML(SortedMap<String, String> signParams)
	{
		StringBuffer str = new StringBuffer();
		str.append("<xml>");
		str.append("<appid>%s</appid>");
		str.append("<body>%s</body>");
		str.append("<mch_id>%s</mch_id>");
		str.append("<nonce_str>%s</nonce_str>");
		str.append("<notify_url>%s</notify_url>");
		str.append("<openid>%s</openid>");
		str.append("<out_trade_no>%s</out_trade_no>");
		str.append("<spbill_create_ip>%s</spbill_create_ip>");
		str.append("<total_fee>%s</total_fee>");
		str.append("<trade_type>%s</trade_type>");
		str.append("<sign>%s</sign>");
		// str.append("<attach>%s</attach>");

		str.append("</xml>");
		String r = String.format(str.toString(), signParams.get("appid"), signParams.get("body"), signParams.get("mch_id"), signParams.get("nonce_str"), signParams.get("notify_url"), signParams.get("openid"), signParams.get("out_trade_no"),
				signParams.get("spbill_create_ip"), signParams.get("total_fee"), signParams.get("trade_type"), signParams.get("sign"));
		log.debugf("xml contents is %s", r);

		return r;

	}

	@Override
	public String sendCustomMsg(NutMap msg)
	{
		// TODO Auto-generated method stub

		log.debugf("send custom message=%s", msg);

		postJson(sendCustomeUrl, msg);

		return null;
	}

}
