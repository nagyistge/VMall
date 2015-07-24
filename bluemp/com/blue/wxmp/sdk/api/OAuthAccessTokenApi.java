package com.blue.wxmp.sdk.api;

import java.util.HashMap;

import org.nutz.http.Http;
import org.nutz.http.Response;
import org.nutz.json.Json;

/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 */
public class OAuthAccessTokenApi
{

	// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

	public static String oauthurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

	// public static String oauthurl =
	// "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";

	public static String getuserinfourl = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

	// private static AccessToken accessToken;

	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getAccessToken(String code)
	{
		String appId = ApiConfigKit.apiConfig.getAppId();
		String appSecret = ApiConfigKit.apiConfig.getAppSecret();

		String url = String.format(OAuthAccessTokenApi.url, appId, appSecret, code);
		Response response = Http.post2(url, null, 5 * 1000);

		if (response.isOK())
		{
			return Json.fromJson(HashMap.class, response.getContent());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getUserinfo(String ac, String openid)
	{

		Response response = Http.post2(String.format(getuserinfourl, ac, openid), null, 5 * 1000);

		if (response.isOK())
		{
			return Json.fromJson(HashMap.class, response.getContent());
		}
		return null;
	}

}
