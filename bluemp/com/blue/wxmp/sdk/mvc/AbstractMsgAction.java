package com.blue.wxmp.sdk.mvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.view.HttpStatusView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.blue.wxmp.sdk.api.OAuthAccessTokenApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;
import com.blue.wxmp.sdk.handle.WxHandler;
import com.blue.wxmp.sdk.util.StringUitls;
import com.blue.wxmp.sdk.util.WxUtils;
import com.skynet.framework.services.function.StringToolKit;

public abstract class AbstractMsgAction
{

	protected WxHandler wxHandler;

	public static Log log = Logs.get();

	@At("/wx")
	public View wxindex(HttpServletRequest req) throws IOException
	{
		WxHandler w = getWxHandler();
		return WxUtils.handle(w, req);
	}

	public WxHandler getWxHandler()
	{
		return wxHandler;
	}

	@At("/oauth")
	public View oauth(String code, String state, String info) throws Exception
	{

		log.debugf("received oauth request from Weixin Server and code is[%s] state=%s", code, state);
		HashMap<String, String> ac = OAuthAccessTokenApi.getAccessToken(code);

		if (ac == null)
		{
			log.warn("error code or request");
			return HttpStatusView.HTTP_404;
		}
		else
		{
			String url = "";
			url = BlueDes.decrypt(info);
			if (url.indexOf("?") > 0)
			{
				url += "&";
			}
			else
			{
				url += "?";
			}
			
			url += "openid=" + ac.get("openid");
			log.debugf("url=%s", url);

			HashMap<String, String> murl = StringUitls.parseFromUrl(url);
			url = murl.get("original_url") + "?info=" + BlueDes.encrypt(Json.toJson(murl));
			log.debugf("encrypt url = %s", url);
			
			HashMap<String, String> oldui = new HashMap<String, String>();
			log.debugf("oldui=%s", oldui); // 增加个人详细信息
			
			HashMap<String, String> newui =OAuthAccessTokenApi.getUserinfo(ac.get("access_token"), ac.get("openid"));
			log.debugf("newui=%s", newui); // 增加个人详细信息
			
			// 蒲剑增加
			String newwxopenid = StringToolKit.formatText(ac.get("openid"));
			String oldwxopenid = StringToolKit.formatText(murl.get("recommender"));
			// set_author(oldwxopenid, newwxopenid);
			set_author(oldui, newui);
			
			return new ViewWrapper(new ServerRedirectView(url + "&v=" + System.currentTimeMillis()), null);
		}
		

	}
	
	protected abstract void set_author(String oldwxopenid, String newwxopenid) throws Exception;
	
	protected abstract void set_author(Map oldui, Map newui) throws Exception;
}
