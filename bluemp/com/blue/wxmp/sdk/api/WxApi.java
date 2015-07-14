package com.blue.wxmp.sdk.api;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.util.NutMap;

public interface WxApi {

	String getAccessToken();

	String getCompleteUri(HttpServletRequest req);
	
	public NutMap genJsSDKConfig(String url, String... jsApiList);
	
//	String sendTemplateMsg();
	

	NutMap sendTemplateMsg(NutMap body);
	
	String getPrepayId(String body, String notifyurl, String orderno, String mchid, String amt, String spbill_create_ip, String openId, String payKey); 
	
	String sendCustomMsg(NutMap msg);
	
	public NutMap getQrcodeUrl(String scene_str);
	
}
