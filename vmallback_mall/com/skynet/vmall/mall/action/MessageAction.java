package com.skynet.vmall.mall.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;
import com.skynet.app.dictionary.service.DictionaryService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.service.MaterialService;
import com.skynet.vmall.base.service.MemberService;

@IocBean
@At("/mall/message")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
public class MessageAction
{

	@Inject
	WxApi myWxApi;

	@Inject
	MemberService memberService;

	@Inject
	MaterialService materialService;
	
	@Inject
	DictionaryService dictionaryService;
	
	private String serverprefix;

	@At("/input")
	@Ok("->:/page/mall/message/input.ftl")
	public Map input(@Param("..") Map map) throws Exception
	{
		System.out.println(map);
		Map ro = new DynamicObject();
		return ro;
	}

	@At("/sendmsg")
	@Ok("raw")
	public String sendmsg(@Param("..") Map map) throws Exception
	{
		if(StringToolKit.isBlank(serverprefix))
		{
			serverprefix = dictionaryService.locateBy(Cnd.where("dkey", "=", "app.system.material.serverprefix")).getFormatAttr("dvalue");
		}
		
		List<Member> members = memberService.sdao().query(Member.class, Cnd.where("1", "=", 1));

		String materialid = StringToolKit.formatText((String) map.get("material_id"));
		if (StringToolKit.isBlank(materialid))
		{
			return "ERROR";
		}

		DynamicObject material = materialService.locate(materialid);

		NutMap msg = new NutMap();
		NutMap news = new NutMap();

		List<NutMap> ats = new ArrayList<NutMap>();
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		String getwxmsgtype = "news";

		String title = material.getFormatAttr("title");
		String description = material.getFormatAttr("description");
		String content = material.getFormatAttr("content");
		String geturl = material.getFormatAttr("linkurl");
		String getpicurl = material.getFormatAttr("pic");

		NutMap n = new NutMap();
		n.put("title", title);
		n.put("description", description);
		n.put("content", content);

		// https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
		String realurl = geturl; // 真实的url地址，注意不要加项目名称
		String enurl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=" + BlueDes.encrypt(realurl);

		String lasturl = String.format(url, ApiConfigKit.apiConfig.getAppId(), enurl);
		String picurl = serverprefix + "/" + getpicurl;

		n.put("url", lasturl);
		n.put("picurl", picurl);
		ats.add(n);

		news.put("articles", ats);

		for (int i = 0; i < members.size(); i++)
		{

			String wxopenid = members.get(i).getWxopenid();
			if (StringToolKit.isBlank(wxopenid))
			{
				continue;
			}

			msg.put("news", news);
			msg.put("touser", wxopenid);
			msg.put("msgtype", getwxmsgtype);

			myWxApi.sendCustomMsg(msg);
		}

		return "SUCCESS";
	}
}
