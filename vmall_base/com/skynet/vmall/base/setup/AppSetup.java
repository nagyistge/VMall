package com.skynet.vmall.base.setup;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.blue.wxmp.sdk.api.AccessTokenApi;
import com.blue.wxmp.sdk.api.ApiConfig;
import com.blue.wxmp.sdk.api.ApiConfigKit;

public class AppSetup implements Setup
{

	private static final Log log = Logs.getLog(AppSetup.class);

	public void destroy(NutConfig config)
	{
		// TODO Auto-generated method stub

	}

	public void init(NutConfig config)
	{
		// TODO Auto-generated method stub

		log.debugf("start init app [%s]setup", "gzh test");
		Ioc ioc = config.getIoc();

		PropertiesProxy pp = ioc.get(PropertiesProxy.class, "config");
		ApiConfig ac = new ApiConfig();
		ac.setToken(pp.get("weixin_token"));
		ac.setAppId(pp.get("weixin_appid"));
		ac.setAppSecret(pp.get("weixin_appsecret"));
		ac.setServercontext(pp.get("weixin_servercontext"));
		ac.setMchid(pp.get("weixin_mchid"));
		ac.setKey(pp.get("weixin_paykey"));
		ApiConfigKit ack = new ApiConfigKit();
		ack.setApiConfig(ac);

		log.debugf("init config completed. and get access_token=%s", AccessTokenApi.getAccessToken().getAccessToken());

	}
}