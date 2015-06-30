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
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Event;
import com.skynet.vmall.base.pojo.EventItem;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.service.TagService;
import com.skynet.vmall.goods.service.GoodsClassService;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/mall/mall")
public class MallAction extends BaseAction
{
	@Inject
	WxApi myWxApi;

	@Inject
	WXActionHelper myWxHelper;

	@Inject
	private GoodsClassService goodsclassService;

	@Inject
	private GoodsService goodsService;

	@Inject
	private TagService tagService;

	@At("/index")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		// 系统微信配置信息
		HttpServletRequest req = Mvcs.getReq();
		HttpSession session = Mvcs.getHttpSession(true);
		String info = (String) session.getAttribute(GlobalConstants.sys_wxinfo);
		if (!StringToolKit.isBlank(info))
		{
			NutMap mapwx = myWxHelper.wx_minfo(info, req);
			ro.put("jscfg", mapwx.get("jscfg"));
			ro.put("shareurl", mapwx.get("shareurl"));
			ro.put("openid", mapwx.get("openid"));
			ro.put("recommender", mapwx.get("recommender"));
		}

		// 查询首页海报1级分类
		StringBuffer sql = new StringBuffer();
		sql.append(" select goodsclass.* ").append("\n");
		sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goodsclass.id = tag.objid ").append("\n");
		sql.append("    and tag.title = '首页海报1级分类' ").append("\n");
		sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

		List<DynamicObject> goodsclasses = goodsclassService.sdao().queryForList(sql.toString());

		for (int i = 0; i < goodsclasses.size(); i++)
		{
			DynamicObject goodsclass = goodsclasses.get(i);

			// 查询首页海报3级分类
			sql = new StringBuffer();
			sql.append(" select goodsclass.* ").append("\n");
			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
			sql.append("    and goodsclass.id = tag.objid ").append("\n");
			sql.append("    and tag.title = '首页海报3级分类' ").append("\n");
			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

			List<DynamicObject> subpostgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());

			// 查询首页3级分类
			sql = new StringBuffer();
			sql.append(" select goodsclass.* ").append("\n");
			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
			sql.append("    and goodsclass.id = tag.objid ").append("\n");
			sql.append("    and tag.title = '首页3级分类' ").append("\n");
			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

			List<DynamicObject> subgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());

			// List<DynamicObject> subgoodsclasses =
			// goodsclassService.findByCond(Cnd.where("supid", "=",
			// goodsclass.getFormatAttr("id")));
			goodsclass.setObj("subpostgoodsclasses", subpostgoodsclasses);
			goodsclass.setObj("subgoodsclasses", subgoodsclasses);
		}

		ro.put("goodsclasses", goodsclasses);
		return ro;
	}

	// 非微信环境测试访问地址
	@At("/index_test")
	@Ok("->:/page/mall/mall/index_test.ftl")
	public Map index_test(@Param("..") Map map) throws Exception
	{
		// 查询首页海报1级分类
		StringBuffer sql = new StringBuffer();
		sql.append(" select goodsclass.* ").append("\n");
		sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goodsclass.id = tag.objid ").append("\n");
		sql.append("    and tag.title = '首页海报1级分类' ").append("\n");
		sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

		List<DynamicObject> goodsclasses = goodsclassService.sdao().queryForList(sql.toString());

		for (int i = 0; i < goodsclasses.size(); i++)
		{
			DynamicObject goodsclass = goodsclasses.get(i);

			// 查询首页海报3级分类
			sql = new StringBuffer();
			sql.append(" select goodsclass.* ").append("\n");
			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
			sql.append("    and goodsclass.id = tag.objid ").append("\n");
			sql.append("    and tag.title = '首页海报3级分类' ").append("\n");
			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

			List<DynamicObject> subpostgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());

			// 查询首页3级分类
			sql = new StringBuffer();
			sql.append(" select goodsclass.* ").append("\n");
			sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and goodsclass.internal like ").append(SQLParser.charLikeRightValue(goodsclass.getFormatAttr("internal"))).append("\n");
			sql.append("    and goodsclass.id = tag.objid ").append("\n");
			sql.append("    and tag.title = '首页3级分类' ").append("\n");
			sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");

			List<DynamicObject> subgoodsclasses = goodsclassService.sdao().queryForList(sql.toString());

			// List<DynamicObject> subgoodsclasses =
			// goodsclassService.findByCond(Cnd.where("supid", "=",
			// goodsclass.getFormatAttr("id")));
			goodsclass.setObj("subpostgoodsclasses", subpostgoodsclasses);
			goodsclass.setObj("subgoodsclasses", subgoodsclasses);
		}

		ro.put("goodsclasses", goodsclasses);
		return ro;
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
				String enurl = ApiConfigKit.apiConfig.getServercontext() + "/oauth.action?info=" + BlueDes.encrypt(realurl);

				String lasturl = String.format(url, ApiConfigKit.apiConfig.getAppId(), enurl);	
				String picurl = ApiConfigKit.apiConfig.getServercontext() + "/" + eventitem.getPic();
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
}
