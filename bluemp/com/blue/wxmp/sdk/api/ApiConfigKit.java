package com.blue.wxmp.sdk.api;


public class ApiConfigKit {

	
	// 开发模式将输出消息交互 xml 到控制台
	private static boolean devMode = false;
	
	public static ApiConfig apiConfig;
	
	public static void setDevMode(boolean devMode) {
		ApiConfigKit.devMode = devMode;
	}
	
	public static boolean isDevMode() {
		return devMode;
	}
	
	
	
	@SuppressWarnings("static-access")
	public void setApiConfig(ApiConfig apiConfig){
		this.apiConfig = apiConfig;
	}
	
}
