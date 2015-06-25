package com.skynet.vmall.wx.action;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Logs;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.OAuthAccessTokenApi;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;

@InjectName("myWxHelper")
@IocBean
public class WXActionHelper
{
	@Inject
	WxApi myWxApi;

	public NutMap wx_minfo(String info, HttpServletRequest req) throws Exception
	{
		String uri = myWxApi.getCompleteUri(req);// 获取完整uri，否则jsticket无法使用
		Logs.get().debugf("request url=%s", uri);

		String decinfo = BlueDes.decrypt(info);
		NutMap minfo = Json.fromJson(NutMap.class, decinfo);

		return wx_init(uri, minfo);
	}

	public NutMap wx_init(String uri, NutMap minfo) throws Exception
	{
		String shareurl = wx_shareurl(uri, minfo);

		NutMap jscfg = wx_jsconfig(uri);

		minfo.put("shareurl", shareurl);
		minfo.put("jscfg", jscfg);

		return minfo;
	}

	// 微信页面交互JS认证
	private NutMap wx_jsconfig(String uri) throws Exception
	{
		NutMap jscfg = myWxApi.genJsSDKConfig(uri, "onMenuShareTimeline", "onMenuShareAppMessage");
		return jscfg;
	}

	// 微信推荐链接等信息
	private String wx_shareurl(String uri, NutMap minfo) throws Exception
	{
		NutMap rowx = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=";
		String realurl = "/author/login/wxlogin.action?recommender=" + minfo.get("openid"); // minfo.get("openid")就是刚点进来链接的人;
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);

		System.out.println("url:" + url);
		System.out.println("redirecturl:" + redirecturl);

		return url;
	}
}
