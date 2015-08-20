package com.skynet.vmall.mall.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.blue.wxmp.sdk.api.WxApi;
import com.blue.wxmp.sdk.encrypt.BlueDes;
import com.skynet.app.dictionary.service.DictionaryService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.Event;
import com.skynet.vmall.base.pojo.EventItem;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.service.EventItemGoodsService;
import com.skynet.vmall.base.service.EventItemService;
import com.skynet.vmall.base.service.EventService;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsService;
import com.skynet.vmall.base.service.TagService;
import com.skynet.vmall.goods.service.AppGoodsService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/mall/mall")
public class MallAction
{
	@Inject
	WxApi myWxApi;

	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private DictionaryService dictionaryService;	
	
	@Inject
	private GoodsService goodsService;
	
	@Inject
	private GoodsClassService goodsclassService;
	
	@Inject
	private AppGoodsService appgoodsService;	

	@Inject
	private TagService tagService;
	
	@Inject
	private EventService eventService;	
	
	@Inject
	private EventItemService eventitemService;
	
	@Inject
	private EventItemGoodsService eventitemgoodsService;

	@At("/wxindex")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map wxindex(String info) throws Exception
	{
		// 系统微信配置信息
		HttpServletRequest req = Mvcs.getReq();
		Map map = myWxHelper.decrypt_info(info);
		return index(map);
	}
	
	@At("/index")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		
		ro.put("jscfg", jscfg());
		ro.put("wxshare", shareurl_wxindex(map));
		
		String jsdebug = StringToolKit.formatText(dictionaryService.locateBy(Cnd.where("dkey", "=", "app.system.weixin.jsdebug")).getFormatAttr("dvalue"),"false");		
		ro.put("jsdebug", jsdebug);		
		return ro;
	}	
	
	protected Map jscfg() throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		String uri = myWxHelper.wx_uri(req);
		NutMap jscfg =  myWxHelper.wx_jsconfig(uri);
		return jscfg;
	}
	
	protected Map shareurl_wxindex(Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		map.put("openid", userwxopenid);		

		String shareurl =  myWxHelper.wx_shareurl("author/login/wxlogin.action", userwxopenid);

		String title = VMallConstants.vmallname;
		String imgurl = ApiConfigKit.apiConfig.getServercontext() + "/image/vmall_logo.jpg";
		String desc = "好朋友，上天狗。 还等什么呢，现在就关注我们，开始一段轻松愉快的购物吧。";
		DynamicObject wxshare = new DynamicObject();
		wxshare.put("title", StringToolKit.formatText(title));
		wxshare.put("desc", StringToolKit.formatText(desc));
		wxshare.put("imgUrl", StringToolKit.formatText(imgurl));
		wxshare.put("shareurl", shareurl);
		
		return wxshare;
	}
	

	
	@At("/index_test")
	@Ok("->:/page/mall/mall/index_test.ftl")
	public Map index_test(@Param("..") Map map) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;		
	}

	// 
	@At("/topgoods")
	@Ok("json")
	public List<DynamicObject> topgoods(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> datas = appgoodsService.topgoods(map);
		return datas;
	}
	
	@At("/eventpromotemsg")
	@Ok("raw")
	public String eventpromotemsg(String eventid) throws Exception
	{
		Event event = tagService.sdao().fetch(Event.class, eventid);
		List<EventItem> eventitems = tagService.sdao().query(EventItem.class, Cnd.where("eventid", "=", eventid));
		List<Member> members = tagService.sdao().query(Member.class, Cnd.where("1", "=", "1"));

		EventItem eventitem = new EventItem();

		for (int i = 0; i < members.size(); i++)
		{

			String wxopenid = members.get(i).getWxopenid();
			if (StringToolKit.isBlank(wxopenid))
			{
				continue;
			}

			NutMap msg = new NutMap();
			NutMap news = new NutMap();

			List<NutMap> ats = new ArrayList<NutMap>();
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

			for (int j = 0; j < eventitems.size(); j++)
			{
				eventitem = eventitems.get(j);
				NutMap n = new NutMap();
				n.put("title", eventitem.getTitle());
				n.put("description", eventitem.getDescription());

				// https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
				String realurl = eventitem.getUrl(); // 真实的url地址，注意不要加项目名称
				realurl += "&eventid=" + eventid + "&eventitemid=" + eventitem.getId();
				String enurl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=" + BlueDes.encrypt(realurl);

				String lasturl = String.format(url, ApiConfigKit.apiConfig.getAppId(), enurl);
				String picurl = "";
				if (j == 0)
				{
					picurl = ApiConfigKit.apiConfig.getServercontext() + "/" + eventitem.getPic();
				}
				else
				{
					picurl = ApiConfigKit.apiConfig.getServercontext() + "/" + eventitem.getPic();
				}
				n.put("url", lasturl);
				n.put("picurl", picurl);
				ats.add(n);
			}

			news.put("articles", ats);

			msg.put("news", news);
			msg.put("touser", wxopenid);
			msg.put("msgtype", event.getWxmsgtype());

			myWxApi.sendCustomMsg(msg);
		}

		return "SUCCESS";
	}
	
	@At("/eventitemlook")
	@Ok("->:/page/mall/mall/eventitemlook.ftl")
	public Map eventitemlook(String info) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		Map map = myWxHelper.decrypt_info(info);
		String eventitemid = StringToolKit.formatText((String)map.get("eventitemid"));
		
		DynamicObject eventitem = eventitemService.locate(eventitemid);
		List<DynamicObject> eventitemgoodses = eventitemgoodsService.findByCond(Cnd.where("eventitemid", "=", eventitemid));
		Map remap = new DynamicObject();
		remap.put("eventitemgoodses", eventitemgoodses);

		for(int i=0;i<eventitemgoodses.size();i++)
		{
			DynamicObject eventitemgoods = eventitemgoodses.get(i);
			String realurl = "goods/goods/eventlook.action?eventitemgoodsid="+eventitemgoods.getFormatAttr("id");
			String lasturl = myWxHelper.encrypt_info(realurl);
			eventitemgoodses.get(i).setAttr("lasturl", lasturl);
		}
		
		return remap;
	}
}
