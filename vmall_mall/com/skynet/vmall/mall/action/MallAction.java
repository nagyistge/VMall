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
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.pojo.Event;
import com.skynet.vmall.base.pojo.EventItem;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.service.EventItemGoodsService;
import com.skynet.vmall.base.service.EventItemService;
import com.skynet.vmall.base.service.EventService;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.TagService;
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
	private GoodsClassService goodsclassService;

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
	public Map wxindex(@Param("..") Map map) throws Exception
	{
		// 系统微信配置信息
		HttpServletRequest req = Mvcs.getReq();
		HttpSession session = Mvcs.getHttpSession(true);
		String info = req.getParameter("info");
		Map wxinfo = myWxHelper.wx_minfo(info, req);

		DynamicObject ro = new DynamicObject();
		ro.put("jscfg", wxinfo.get("jscfg"));
		ro.put("shareurl", wxinfo.get("shareurl"));
		ro.put("openid", wxinfo.get("openid"));
		ro.put("recommender", wxinfo.get("recommender"));

		return index(map);
	}
	
	@At("/index")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
//		// 查询首页海报1级分类
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select goodsclass.* ").append("\n");
//		sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
//		sql.append("  where 1 = 1 ").append("\n");
//		sql.append("    and goodsclass.id = tag.objid ").append("\n");
//		sql.append("    and tag.title = '首页海报1级分类' ").append("\n");
//		sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");
//
//		List<DynamicObject> goodsclasses = goodsclassService.sdao().queryForList(sql.toString());
//
//		for (int i = 0; i < goodsclasses.size(); i++)
//		{
//			DynamicObject goodsclass = goodsclasses.get(i);
//
//			// 查询首页海报3级分类
//			sql = new StringBuffer();
//			sql.append(" select goodsclass.* ").append("\n");
//			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
//			sql.append("  where 1 = 1 ").append("\n");
//			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
//			sql.append("    and goodsclass.id = tag.objid ").append("\n");
//			sql.append("    and tag.title = '首页海报3级分类' ").append("\n");
//			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");
//
//			List<DynamicObject> subpostgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());
//
//			// 查询首页3级分类
//			sql = new StringBuffer();
//			sql.append(" select goodsclass.* ").append("\n");
//			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
//			sql.append("  where 1 = 1 ").append("\n");
//			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
//			sql.append("    and goodsclass.id = tag.objid ").append("\n");
//			sql.append("    and tag.title = '首页3级分类' ").append("\n");
//			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");
//
//			List<DynamicObject> subgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());
//
//			// List<DynamicObject> subgoodsclasses =
//			// goodsclassService.findByCond(Cnd.where("supid", "=",
//			// goodsclass.getFormatAttr("id")));
//			goodsclass.setObj("subpostgoodsclasses", subpostgoodsclasses);
//			goodsclass.setObj("subgoodsclasses", subgoodsclasses);
//		}
//
//		DynamicObject ro = new DynamicObject();
//		ro.put("goodsclasses", goodsclasses);
		
		DynamicObject ro = new DynamicObject();
		return ro;
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
		StringBuffer sql = new StringBuffer();
		sql.append(" select goods.* ").append("\n");
		sql.append("   from t_app_goods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and ctype = '货品' ").append("\n");
		sql.append("    and defspec = '是' ").append("\n");

		List<DynamicObject> datas = goodsclassService.sdao().queryForList(sql.toString(), 1, 10);
		if(datas.size()%2==1)
		{
			datas.remove(datas.size()-1);
		}
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
