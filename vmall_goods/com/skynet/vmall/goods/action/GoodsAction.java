package com.skynet.vmall.goods.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Event;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.service.EventItemGoodsService;
import com.skynet.vmall.base.service.EventItemService;
import com.skynet.vmall.base.service.EventService;
import com.skynet.vmall.goods.service.GoodsClassService;
import com.skynet.vmall.goods.service.GoodsClassSpecService;
import com.skynet.vmall.goods.service.GoodsPhotoService;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.member.service.MemberService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/goods/goods")
public class GoodsAction extends BaseAction
{
	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private MemberService memberService;

	@Inject
	private GoodsClassSpecService goodsclassspecService;

	@Inject
	private GoodsClassService goodsclassService;

	@Inject
	private GoodsService goodsService;

	@Inject
	private GoodsPhotoService goodsphotoService;
	
	@Inject
	private EventService eventService;
	
	@Inject
	private EventItemService eventitemService;
	
	@Inject
	private EventItemGoodsService eventitemgoodsService;

	@At("/index")
	@Ok("->:/page/goods/goods/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> goods = goodsService.browse(map);
		ro.put("goods", goods);
		return ro;
	}

	@At("/browse")
	@Ok("->:/page/goods/goods/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "5"));
		map.put("state", "新建");
		List<DynamicObject> goods = goodsService.browse(map);
		ro.put("goods", goods);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}

	// 浏览指定排名的商品
	@At("/channel")
	@Ok("->:/page/goods/goods/channel.ftl")
	public Map channel(@Param("..") Map map) throws Exception
	{
		String classid = (String) map.get("classid");
		DynamicObject goodsclass = goodsclassService.locate(classid);
		DynamicObject supgoodsclass = goodsclassService.locateBy(Cnd.where("id", "=", goodsclass.getFormatAttr("supid")));
		List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", classid));

		ro.put("supgoodsclass", supgoodsclass);
		ro.put("goodsclass", goodsclass);
		ro.put("subgoodsclasses", subgoodsclasses);

		return ro;
	}

	// 商品浏览
	@At("/channelshow")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map channelshow(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		String classid = (String) map.get("classid");

		DynamicObject goodsclass = goodsclassService.locate(classid);
		DynamicObject supgoodsclass = goodsclassService.locateBy(Cnd.where("id", "=", goodsclass.getFormatAttr("supid")));
		List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", classid));

		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "10"));
		map.put("ctype", "货品");
		map.put("defspec", "是");
		map.put("internal", goodsclass.getFormatAttr("internal"));
		List<DynamicObject> goodses = goodsService.channel(map);

		ro.put("supgoodsclass", supgoodsclass);
		ro.put("goodsclass", goodsclass);
		ro.put("subgoodsclasses", subgoodsclasses);
		ro.put("goodses", goodses);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}

	@At("/test")
	@Ok("json")
	public String[] test(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		String[] a = new String[100];
		for (int i = 0; i < 100; i++)
		{
			a[i] = String.valueOf(i);
		}
		return a;
	}

	// 商品浏览
	@At("/show")
	@Ok("->:/page/goods/goods/show.ftl")
	public Map show(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "5"));
		List<DynamicObject> goods = goodsService.browse(map);
		ro.put("goods", goods);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}

	// 商品浏览
	@At("/look")
	@Ok("->:/page/goods/goods/look.ftl")
	public Map look(@Param("..") Map map) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		String uri = myWxHelper.wx_uri(req);
		String shareurl =  myWxHelper.wx_shareurl(uri, new NutMap(map));
		NutMap jscfg =  myWxHelper.wx_jsconfig(uri);
		ro.put("shareurl", shareurl);
		ro.put("jscfg", jscfg);
		
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		DynamicObject member = memberService.locateBy(Cnd.where("wxopenid", "=", userwxopenid));

		String id = (String) map.get("id"); // 商品标识
		String eventid = (String) map.get("eventid"); //活动标识
		String eventitemid = (String) map.get("eventitemid"); //子活动标识
		
		// 记录浏览人气值
		DynamicObject goods = goodsService.locate(id);
		goodsService.sdao().update(Goods.class, Chain.make("popular", Types.parseInt(goods.getFormatAttr("popular"), 0) + 1), Cnd.where("id", "=", id));
		
		// 如果从参与活动入口，产品价格按照参与活动价格为准。
		if(!(StringToolKit.isBlank(eventid))&&!(StringToolKit.isBlank(eventitemid)))
		{
			DynamicObject eventitemgoods = eventitemgoodsService.locateBy(Cnd.where("eventitemid", "=", eventitemid).and("goodsid", "=", id));
			goods.setAttr("saleprice", eventitemgoods.getFormatAttr("saleprice"));
			goods.setAttr("promoteprice", eventitemgoods.getFormatAttr("promoteprice"));

			
			DynamicObject event = eventService.locate(eventid);
			DynamicObject eventitem = eventitemService.locate(eventitemid);

			ro.put("event", event);
			ro.put("eventitem", eventitem);
			ro.put("eventitemgoods", eventitemgoods);
			
			ro.put("eventitemid", eventitemid);
		}

		List<DynamicObject> goodsclassspeces = goodsclassspecService.getGoodsClassSpeces(goods.getFormatAttr("classid"));
		List<DynamicObject> goodsspecs = goodsService.findgoodsspec(goods.getFormatAttr("supid"));
		List<DynamicObject> currentgoodsspecs = goodsService.findgoodsspec(goods.getFormatAttr("id"));

		List<DynamicObject> likegoodses = goodsService.guestlike(map);

		ro.put("member", member);
		ro.put("goods", goods);
		ro.put("goodsclassspeces", goodsclassspeces);
		ro.put("goodsspecs", goodsspecs);
		ro.put("currentgoodsspecs", currentgoodsspecs);
		ro.put("likegoodses", likegoodses);

		return ro;
	}

	// 商品浏览
	@At("/guestlike")
	@Ok("json")
	public Map guestlike(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> likegoodses = goodsService.guestlike(map);
		ro.put("likegoodses", likegoodses);
		return ro;
	}

	// 商品详情
	@At("/detail")
	@Ok("->:/page/goods/goods/detail.ftl")
	public Map detail(@Param("..") Map map) throws Exception
	{
		String id = (String) map.get("id");
		DynamicObject goods = goodsService.locate(id);

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goodsphoto goodsphoto ");
		sql.append("  where 1 = 1 ");
		sql.append("    and goodsid = ").append(SQLParser.charValue(id));
		List goodsphotoes = goodsphotoService.queryForList(sql.toString());

		ro.put("goods", goods);
		ro.put("goodsphotoes", goodsphotoes);
		return ro;
	}

	// 商品浏览
	@At("/getgoodsbyspec")
	@AdaptBy(type = JsonAdaptor.class)
	@Ok("json")
	public Map getgoodsbyspec(@Param("..") Map map) throws Exception
	{
		String supgoodsid = (String) map.get("supgoodsid");
		List<ArrayList<String>> specs = (List<ArrayList<String>>) map.get("specs");

		for (int i = 0; i < specs.size(); i++)
		{
			Object o = specs.get(i);
			System.out.println(specs.get(i));
			System.out.println(o);
		}

		DynamicObject goods = goodsService.getgoodsbyspec(supgoodsid, specs);

		return goods;
	}
	
	// 商品浏览
	@At("/rushlook")
	@Ok("->:/page/goods/goods/look.ftl")
	public Map rushlook(String info) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		Map map = myWxHelper.decrypt_info(info);
		return look(map);
	}

}
