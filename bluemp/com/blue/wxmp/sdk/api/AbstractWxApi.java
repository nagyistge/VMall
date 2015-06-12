package com.blue.wxmp.sdk.api;

import javax.servlet.http.HttpServletRequest;

import org.nutz.http.Request;
import org.nutz.http.Request.METHOD;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

public abstract class AbstractWxApi implements WxApi {
	protected final static Log log = Logs.get();

	private static String sendTemplateMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send";

	@Override
	public String getCompleteUri(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String uri = Mvcs.getRequestPath(req) + ".action" + (req.getQueryString() != null ? "?" + req.getQueryString() : "");
		return ApiConfigKit.apiConfig.getServercontext() + uri;
	}

	@Override
	public String getAccessToken() {
		// TODO Auto-generated method stub
		return AccessTokenApi.getAccessToken().getAccessToken();
	}

	public NutMap genJsSDKConfig(String url, String... jsApiList) {
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
	public NutMap sendTemplateMsg(NutMap body) {

		return postJson(sendTemplateMsgUrl, body);
	}

	protected NutMap postJson(String uri, NutMap body) {
		return call(uri, METHOD.POST, Json.toJson(body));
	}

	protected NutMap call(String URL, METHOD method, String body) {
		String token = getAccessToken();
		if (URL.contains("?")) {
			URL += "&access_token=" + token;
		} else {
			URL += "?access_token=" + token;
		}
		if (log.isInfoEnabled()) {
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

//	@Override
//	public WxPrepayIdResult getPrepayId(String openId, String outTradeNo, double amt, String body, String tradeType, String ip, String callbackUrl) {
//		String nonce_str = System.currentTimeMillis() + "";
//
	
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", wxMpConfigStorage.getAppId());
//		packageParams.put("mch_id", wxMpConfigStorage.getPartnerId());
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", body);
//		packageParams.put("out_trade_no", outTradeNo);
//
//		packageParams.put("total_fee", (int) (amt * 100) + "");
//		packageParams.put("spbill_create_ip", ip);
//		packageParams.put("notify_url", callbackUrl);
//		packageParams.put("trade_type", tradeType);
//		packageParams.put("openid", openId);
//
//		String sign = WxCryptUtil.createSign(packageParams, wxMpConfigStorage.getPartnerKey());
//		String xml = "<xml>" + "<appid>" + wxMpConfigStorage.getAppId() + "</appid>" + "<mch_id>" + wxMpConfigStorage.getPartnerId() + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<sign>" + sign + "</sign>" + "<body><![CDATA[" + body + "]]></body>" + "<out_trade_no>" + outTradeNo + "</out_trade_no>" + "<total_fee>" + packageParams.get("total_fee") + "</total_fee>" + "<spbill_create_ip>" + ip + "</spbill_create_ip>" + "<notify_url>" + callbackUrl + "</notify_url>" + "<trade_type>" + tradeType + "</trade_type>" + "<openid>" + openId + "</openid>" + "</xml>";
//
//		HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
//		if (httpProxy != null) {
//			RequestConfig config = RequestConfig.custom().setProxy(httpProxy).build();
//			httpPost.setConfig(config);
//		}
//
//		StringEntity entity = new StringEntity(xml, Consts.UTF_8);
//		httpPost.setEntity(entity);
//		try {
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
//			XStream xstream = XStreamInitializer.getInstance();
//			xstream.alias("xml", WxMpPrepayIdResult.class);
//			WxMpPrepayIdResult wxMpPrepayIdResult = (WxMpPrepayIdResult) xstream.fromXML(responseContent);
//			return wxMpPrepayIdResult;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return new WxMpPrepayIdResult();
//	}
//
//	@Override
//	public Map<String, String> getJSSDKPayInfo(String openId, String outTradeNo, double amt, String body, String tradeType, String ip, String callbackUrl) {
//		WxMpPrepayIdResult wxMpPrepayIdResult = getPrepayId(openId, outTradeNo, amt, body, tradeType, ip, callbackUrl);
//		String prepayId = wxMpPrepayIdResult.getPrepay_id();
//		if (prepayId == null || prepayId.equals("")) {
//			throw new RuntimeException("get prepayid error");
//		}
//
//		Map<String, String> payInfo = new HashMap<String, String>();
//		payInfo.put("appId", wxMpConfigStorage.getAppId());
//		// 支付签名时间戳，注意微信jssdk中的所有使用timestamp字段均为小写。但最新版的支付后台生成签名使用的timeStamp字段名需大写其中的S字符
//		payInfo.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
//		payInfo.put("nonceStr", System.currentTimeMillis() + "");
//		payInfo.put("package", "prepay_id=" + prepayId);
//		payInfo.put("signType", "MD5");
//
//		String finalSign = WxCryptUtil.createSign(payInfo, wxMpConfigStorage.getPartnerKey());
//		payInfo.put("sign", finalSign);
//		return payInfo;
//	}
}
