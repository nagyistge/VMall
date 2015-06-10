package com.blue.wxmp.sdk.api;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.nutz.http.Http;
import org.nutz.http.Response;

/**
 * 认证并获取 access_token API
 * http://mp.weixin.qq.com/wiki/index.php?title=%E8%8E%B7%E5%8F%96access_token
 */
public class AccessTokenApi {

	// "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	// 利用 appId 与 accessToken 建立关联，支持多账户
	private static Map<String, AccessToken> map = new ConcurrentHashMap<String, AccessToken>();

	// private static AccessToken accessToken;

	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	public static AccessToken getAccessToken() {
		String appId = ApiConfigKit.apiConfig.getAppId();
		AccessToken result = map.get(appId);
		if (result != null && result.isAvailable())
			return result;

		refreshAccessToken();
		return map.get(appId);
	}

	/**
	 * 强制更新 access token 值
	 */
	public static synchronized void refreshAccessToken() {
		ApiConfig ac = ApiConfigKit.apiConfig;
		AccessToken result = null;
		for (int i = 0; i < 3; i++) { // 最多三次请求
			String appId = ac.getAppId();
			String appSecret = ac.getAppSecret();
			url += "&appid=" + appId + "&secret=" + appSecret;
			Response rs = Http.get(url);
			result = new AccessToken(rs.getContent());

			if (result.isAvailable())
				break;
		}

		// 三次请求如果仍然返回了不可用的 access token 仍然 put 进去，便于上层通过 AccessToken 中的属性判断底层的情况
		map.put(ac.getAppId(), result);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		ApiConfig ac = new ApiConfig();
		ac.setAppId("wxd986013eeb54f390");
		ac.setAppSecret("65b2afd314e5ef8f4d3a514413ad8907");
		ApiConfigKit akt = new ApiConfigKit();
		akt.setApiConfig(ac);
		AccessToken at = getAccessToken();
		if (at.isAvailable())
			System.out.println("access_token : " + at.getAccessToken());
		else
			System.out.println(at.getErrorCode() + " : " + at.getErrorMsg());
	}
}
