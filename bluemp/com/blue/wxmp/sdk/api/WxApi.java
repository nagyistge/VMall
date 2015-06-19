package com.blue.wxmp.sdk.api;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import org.nutz.lang.util.NutMap;
import org.xml.sax.SAXException;

import com.blue.wxmp.sdk.bean.WxPrepayIdResult;

public interface WxApi {

	String getAccessToken();

	String getCompleteUri(HttpServletRequest req);
	
	public NutMap genJsSDKConfig(String url, String... jsApiList);
	
//	String sendTemplateMsg();
	

	NutMap sendTemplateMsg(NutMap body);
	
	String getPrepayId(String body, String notifyurl, String orderno, String mchid, String amt, String spbill_create_ip, String openId, String payKey); 
	
	
	
}
