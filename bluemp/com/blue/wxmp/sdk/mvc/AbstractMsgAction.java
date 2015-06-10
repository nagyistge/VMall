package com.blue.wxmp.sdk.mvc;

import java.io.IOException;
import java.util.HashMap;

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

public abstract class AbstractMsgAction {

	protected  WxHandler wxHandler;
	public static Log log = Logs.get();

	@At("/wx")
	public View wxindex(HttpServletRequest req) throws IOException {
		
		WxHandler w = getWxHandler();
		return WxUtils.handle(w, req);
	}

	public WxHandler getWxHandler() {
		return wxHandler;
	}

	@At("/oauth")
	public View oauth(String code, String state, String info) throws Exception {

		log.debugf("received oauth request from Weixin Server and code is[%s] state=%s", code, state);
		HashMap<String, String> ac = OAuthAccessTokenApi.getAccessToken(code);

		if (ac == null) {
			log.warn("error code or request");
			return HttpStatusView.HTTP_404;
		} else {
			String url = "";
			url = BlueDes.decrypt(info);
			if (url.indexOf("?") > 0) {
				url += "&";
			} else {
				url += "?";
			}
			url += "openid=" + ac.get("openid");
			log.debugf("url=%s", url);
			
			HashMap<String,String> murl = StringUitls.parseFromUrl(url);
			url = murl.get("original_url")+"?info="+BlueDes.encrypt(Json.toJson(murl));
			
			log.debugf("encrypt url = %s" , url);
			return new ViewWrapper(new ServerRedirectView(url+"&v="+System.currentTimeMillis()), null);
		}

	}
}
