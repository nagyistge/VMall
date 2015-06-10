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
public class JsapiTicketApi {

	// "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	private static String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";

	// 利用 appId 与 accessToken 建立关联，支持多账户
	private static Map<String, JsapiTicket> map = new ConcurrentHashMap<String, JsapiTicket>();

	// private static AccessToken accessToken;

	/**
	 * 从缓存中获取 access token，如果未取到或者 access token 不可用则先更新再获取
	 */
	public static JsapiTicket getJsapiTicket() {
		String appId = ApiConfigKit.apiConfig.getAppId();
		JsapiTicket result = map.get(appId);
		if (result != null && result.isAvailable())
			return result;

		refreshJsapiTicket();
		return map.get(appId);
	}

	/**
	 * 强制更新 access token 值
	 */
	public static synchronized void refreshJsapiTicket() {
		ApiConfig ac = ApiConfigKit.apiConfig;
		JsapiTicket result = null;
		for (int i = 0; i < 3; i++) { // 最多三次请求
			
			Response rs = Http.get(String.format(url, AccessTokenApi.getAccessToken().getAccessToken()));
			result = new JsapiTicket(rs.getContent());

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
		
		JsapiTicket at = getJsapiTicket();
		if (at.isAvailable())
			System.out.println("ticket : " + at.getJsapiTicket());
		else
			System.out.println(at.getErrorCode()+ " : " + at.getErrorMsg());
	}
}
