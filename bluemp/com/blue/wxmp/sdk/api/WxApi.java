package com.blue.wxmp.sdk.api;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.util.NutMap;

public interface WxApi {

	String getAccessToken();

	String getCompleteUri(HttpServletRequest req);
	
	public NutMap genJsSDKConfig(String url, String... jsApiList);
	
//	String sendTemplateMsg();
	

	NutMap sendTemplateMsg(NutMap body);
	
	
	
}
