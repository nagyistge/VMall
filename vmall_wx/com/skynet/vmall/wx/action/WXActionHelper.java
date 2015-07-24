package com.skynet.vmall.wx.action;

import java.util.HashMap;
import java.util.Iterator;
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
	public String wx_shareurl(String uri, Map minfo) throws Exception
	{
		NutMap rowx = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=";

		HttpServletRequest req = Mvcs.getReq();

		String orgin_uri =  uri;
		String realurl = recommender(orgin_uri, minfo);
		
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);

		System.out.println("url:" + url);
		System.out.println("redirecturl:" + redirecturl);

		return url;
	}
	
	// 微信推荐链接等信息
	public String wx_shareurl(String uri, String openid) throws Exception
	{
		NutMap rowx = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=";

		HttpServletRequest req = Mvcs.getReq();

		String orgin_uri =  uri;
		String realurl = recommender(orgin_uri, openid);
		
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);

		System.out.println("url:" + url);
		System.out.println("redirecturl:" + redirecturl);

		return url;
	}

	// 解密URL
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

	public static String recommender(String uri, String openid)
	{
		String uri_new = "";
		String uri_pre = "";
		int rindex = uri.indexOf("?");
		if (rindex > 0)
		{
			uri_pre = uri.substring(0, rindex);
		}
		else
		{
			uri_pre = uri;
		}

		boolean has = false;
		if (rindex > 0)
		{
			String queryString = uri.substring(rindex + 1);
			String[] params = queryString.split("&");
			for (int i = 0; i < params.length; i++)
			{
				String[] pvalues = params[i].split("=");
				if ("recommender".equals(pvalues[0]))
				{
					has = true;
					pvalues[1] = openid;
				}
				uri_new += pvalues[0] + "=" + pvalues[1];
				if (i < params.length - 1)
				{
					uri_new += "&";
				}
			}
			
			if(has==false)
			{
				uri_new += "&recmmender=" + openid;
			}
		}
		else
		{
			uri_new = "recommender=" + openid;
			has = true;
		}
		

		uri_new = uri_pre + "?" + uri_new;

		return uri_new;
	}
	
	public static String recommender(String uri, Map minfo)
	{
		String uri_new = "";
		String uri_pre = "";
		int rindex = uri.indexOf("?");
		if (rindex > 0)
		{
			uri_pre = uri.substring(0, rindex);
		}
		else
		{
			uri_pre = uri;
		}
		
		minfo.put("recommender", StringToolKit.formatText((String) minfo.get("openid")));
		
		Iterator<String> keys = minfo.keySet().iterator();
		int index = 0;
		while(keys.hasNext())
		{
			if(index>0)
			{
				uri_new += "&";
			}
			
			String akey = keys.next();
			uri_new += (akey + "=" + StringToolKit.formatText((String)minfo.get(akey)));
			index++;
		}

		uri_new = uri_pre + "?" + uri_new;

		return uri_new;
	}

	public static void main(String args[]) throws Exception
	{
		String uri = "";
		
		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action";
		System.out.println(recommender(uri, "ABC"));
		
		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?recommender=XYZ";
		System.out.println(recommender(uri, "ABC"));
		
		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?recommender=XYZ&p1=v1";
		System.out.println(recommender(uri, "ABC"));		
		
		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?recommender=ABC";
		System.out.println(recommender(uri, "ABC"));		

		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?p1=v1";
		System.out.println(recommender(uri, "ABC"));

		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?p1=v1&recommender=XYZ";
		System.out.println(recommender(uri, "ABC"));
		
		uri = "http://www.rbtalking.com/vmall/mall/mall/index.action?p1=v1&recommender=XYZ&p2=v2";
		System.out.println(recommender(uri, "ABC"));

	}

}
