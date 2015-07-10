package com.skynet.vmall.wx.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;

import com.blue.wxmp.sdk.api.AbstractWxApi;
import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.OAuthAccessTokenApi;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;
import com.skynet.framework.services.function.StringToolKit;

@InjectName("myWxHelper")
@IocBean
public class WXActionHelper
{
	@Inject
	WxApi myWxApi;
	
	
	public NutMap wx_minfo(String info, HttpServletRequest req) throws Exception
	{
		String decinfo = BlueDes.decrypt(info);
		NutMap minfo = Json.fromJson(NutMap.class, decinfo);
		
		String uri = wx_uri(req);
		String shareurl = wx_shareurl(uri, minfo);
		NutMap jscfg = wx_jsconfig(uri);

		minfo.put("shareurl", shareurl);
		minfo.put("jscfg", jscfg);
		minfo.put("info", info);
		
		return minfo;
	}
	
	public String wx_uri(HttpServletRequest req)
	{
		String uri = myWxApi.getCompleteUri(req);// 获取完整uri，否则jsticket无法使用
		Logs.get().debugf("request url=%s", uri);
		return uri;
	}

	// 微信页面交互JS认证
	public NutMap wx_jsconfig(String uri) throws Exception
	{
		NutMap jscfg = myWxApi.genJsSDKConfig(uri, "onMenuShareTimeline", "onMenuShareAppMessage");
		return jscfg;
	}

	// 微信推荐链接等信息
	public String wx_shareurl(String uri, NutMap minfo) throws Exception
	{
		NutMap rowx = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=";
		// String realurl = "/author/login/wxlogin.action?recommender=" + StringToolKit.formatText((String)minfo.get("openid")); // minfo.get("openid")就是刚点进来链接的人;
		
		HttpServletRequest req = Mvcs.getReq();
		String realurl = req.getServletPath() + "?recommender=" + StringToolKit.formatText((String)minfo.get("openid"));
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);

		System.out.println("url:" + url);
		System.out.println("redirecturl:" + redirecturl);

		return url;
	}

	//解密URL
	public Map decrypt_info(String info) throws Exception
	{
		info = BlueDes.decrypt(info);
		Map<String, String> rinfo = Json.fromJson(HashMap.class, info);
		return rinfo;
	}
	
	// 加密URL
	public String encrypt_info(String realurl) throws Exception
	{
		String url = AbstractWxApi.authorUrl;
		String enurl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=" + BlueDes.encrypt(realurl);
		String lasturl = String.format(url, ApiConfigKit.apiConfig.getAppId(), enurl);
		return lasturl;
	}
}
