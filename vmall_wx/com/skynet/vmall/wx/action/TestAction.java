package com.skynet.vmall.wx.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Mirror;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.OAuthAccessTokenApi;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.bean.WxTemplateData;
import com.blue.wxmp.sdk.encrypt.BlueDes;

@IocBean
public class TestAction {

	private Log log = Logs.getLog(TestAction.class);

	@Inject
	WxApi myWxApi;

	@At("/test")
	@Ok("->:/pages/test.ftl")
	public String test(String echo) {
		echo = Strings.isEmpty(echo) ? "empty" : echo;

		return echo;
	}

	/**
	 * 接受共享给朋友功能的action ， 经过abstractMsgAction的info加密，解密后拿出相关数据，
	 * 数据内容含有打开此链接的人的openid和推荐人recommender的openid
	 * 
	 * @param info
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@At("/sharetofriend")
	@Ok("->:/pages/test.ftl")
	public HashMap sharetofriend(String info, HttpServletRequest req) throws Exception {

		log.debugf("------------------------------info=%s", info);
		info = BlueDes.decrypt(info);
		HashMap<String, String> rinfo = Json.fromJson(HashMap.class, info);
		log.debugf("------------------------------real info=%s", info);

		return rinfo;
	}

	@At("/sendtemplatemsg")
	public NutMap sendtemplatemsg() {
		
		NutMap json = NutMap.NEW();
		json.addv("touser", "ofcJis8jiU2TmU97p-sbOVZYtehs");//接收方的openid
		json.addv("url", "");//点击打开的链接
		json.addv("template_id", "XN0U-lvijnVzSnzH4A4mXfTM0Q9IYC9Y1-Pa0sx3Y6A");// 微信公众号增加的订单模板消息id-最终配置到数据库
		json.addv("topcolor", "#FF0000");

		NutMap data = NutMap.NEW();
		//模板里面的字段值
		data.addv("first", Mirror.me(WxTemplateData.class).born("恭喜你购买成功了~~~~", "#173177"));
		data.addv("orderMoneySum", Mirror.me(WxTemplateData.class).born("600", "#173177"));
		data.addv("orderProductName", Mirror.me(WxTemplateData.class).born("小米note", "#173177"));
		data.addv("Remark", Mirror.me(WxTemplateData.class).born("如有问题请就唧唧歪歪几句，反正我也不理你", "#173177"));
		
		json.addv("data", data);
		
		return myWxApi.sendTemplateMsg(json);
	}

	/**
	 * 样例的首页代码，其中包含 oauth返回的加密info信息，还包含如何得到 jsapiticket的示例
	 * 
	 * @param info
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@At("/index")
	@Ok("->:/index.ftl")
	public NutMap index(String info, HttpServletRequest req) throws Exception {

		String uri = myWxApi.getCompleteUri(req);// 获取完整uri，否则jsticket无法使用
		Logs.get().debugf("request url=%s", uri);

		info = BlueDes.decrypt(info);
		HashMap<String, String> minfo = Json.fromJson(HashMap.class, info);

		NutMap jscfg = myWxApi.genJsSDKConfig(uri, "onMenuShareTimeline", "onMenuShareAppMessage");
		NutMap ro = NutMap.NEW();

		String redirecturl = ApiConfigKit.apiConfig.getServercontext() + "/oauth?info=";
		String realurl = "/sharetofriend?recommender=" + minfo.get("openid"); // minfo.get("openid")就是刚点进来链接的人;
		realurl = BlueDes.encrypt(realurl);

		redirecturl += realurl;

		String url = String.format(OAuthAccessTokenApi.oauthurl, ApiConfigKit.apiConfig.getAppId(), redirecturl);
		
		
		ro.put("shareurl", url);

	
		ro.put("openid", minfo.get("openid"));
		ro.put("jscfg", jscfg);
		
		log.debugf("openid=%s", minfo.get("openid"));

		return ro;
	}
	
	@At("/getprepayid")
	@Ok("raw:json")
	public String getprepayid(String openid,HttpServletRequest req) throws Exception {
		
		String orderno = "order00010000004";
		String amt = "1";
		 
//		myWxApi.getPrepayId(body, notifyurl, orderno, mchid, amt, spbill_create_ip, openId, payKey)
		return myWxApi.getPrepayId("测试订单1", "http://www.tiangouvmall.com/vmall/test", orderno, ApiConfigKit.apiConfig.getMchid(), amt, "10.0.0.1", openid, ApiConfigKit.apiConfig.getKey());
		
	}
}
