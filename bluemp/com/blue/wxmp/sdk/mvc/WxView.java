package com.blue.wxmp.sdk.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.mvc.View;

import com.blue.wxmp.sdk.bean.WxOutMsg;
import com.blue.wxmp.sdk.util.WxUtils;

public class WxView implements View {
	
	public static final View me = new WxView();
	
	public void render(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Throwable {
		if (obj == null) {
			return;
		}
		WxUtils.asXml(resp.getWriter(), (WxOutMsg) obj);
	}
}