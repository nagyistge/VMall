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

import com.blue.wxmp.sdk.api.ApiConfigKit;
import com.skynet.app.dictionary.service.DictionaryService;
import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.service.EventItemGoodsService;
import com.skynet.vmall.base.service.EventItemService;
import com.skynet.vmall.base.service.EventService;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsClassSpecService;
import com.skynet.vmall.base.service.GoodsPhotoService;
import com.skynet.vmall.base.service.GoodsService;
import com.skynet.vmall.base.service.MemberService;
import com.skynet.vmall.goods.service.AppGoodsClassService;
import com.skynet.vmall.goods.service.AppGoodsClassSpecService;
import com.skynet.vmall.goods.service.AppGoodsService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/goods/goods")
public class GoodsAction
{
	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private DictionaryService dictionaryService;	
	
	@Inject
	private MemberService memberService;
	
	@Inject
	private AppGoodsClassSpecService appgoodsclassspecService;

	@Inject
	private AppGoodsClassService appgoodsclassService;
	
	@Inject
	private GoodsClassSpecService goodsclassspecService;

	@Inject
	private GoodsClassService goodsclassService;	
	
	@Inject
	private GoodsService goodsService;	

	@Inject
	private AppGoodsService appgoodsService;
	
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
		List<DynamicObject> goods = appgoodsService.browse(map);
		DynamicObject ro = new DynamicObject();
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
		List<DynamicObject> goods = appgoodsService.browse(map);
		DynamicObject ro = new DynamicObject();
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
		DynamicObject ro = new DynamicObject();
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
		List<DynamicObject> goodses = appgoodsService.channel(map);
		DynamicObject ro = new DynamicObject();
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
		List<DynamicObject> goods = appgoodsService.browse(map);
		DynamicObject ro = new DynamicObject();
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
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		map.put("openid", userwxopenid);
		
		String id = StringToolKit.formatText((String) map.get("id")); // 商品标识
		// 记录浏览人气值
		DynamicObject goods = goodsService.locate(id);
		goodsService.sdao().update(Goods.class, Chain.make("popular", Types.parseInt(goods.getFormatAttr("popular"), 0) + 1), Cnd.where("id", "=", id));
		
		String eventitemgoodsid = StringToolKit.formatText((String) map.get("eventitemgoodsid")); //活动项目商品标识
		
		DynamicObject ro = new DynamicObject();
		ro.put("eventitemgoodsid", eventitemgoodsid);
		
		// 如果从参与活动入口，产品价格按照参与活动价格为准。
		if(!(StringToolKit.isBlank(eventitemgoodsid)))
		{
			DynamicObject eventitemgoods = eventitemgoodsService.locate(eventitemgoodsid);
			goods.setAttr("saleprice", eventitemgoods.getFormatAttr("saleprice"));
			goods.setAttr("promoteprice", eventitemgoods.getFormatAttr("promoteprice"));
			ro.put("eventitemgoods", eventitemgoods);
		}

		List<DynamicObject> goodsclassspeces = appgoodsclassspecService.getGoodsClassSpeces(goods.getFormatAttr("classid"));
		List<DynamicObject> goodsspecs = appgoodsService.findgoodsspec(goods.getFormatAttr("supid"));
		List<DynamicObject> currentgoodsspecs = appgoodsService.findgoodsspec(goods.getFormatAttr("id"));

		List<DynamicObject> likegoodses = appgoodsService.guestlike(map);

		DynamicObject member = memberService.locateBy(Cnd.where("wxopenid", "=", userwxopenid));

		String goodsname = goods.getFormatAttr("cname");
		String promoteprice = goods.getFormatAttr("promoteprice");
		String pic = goods.getFormatAttr("pic");
		
		ro.put("jscfg", jscfg());
		ro.put("wxshare", shareurl_look(goodsname + "【￥" + promoteprice + "】" , pic, map));

		String jsdebug = StringToolKit.formatText(dictionaryService.locateBy(Cnd.where("dkey", "=", "app.system.weixin.jsdebug")).getFormatAttr("dvalue"),"false");		
		ro.put("jsdebug", jsdebug);
		
		ro.put("member", member);
		ro.put("goods", goods);
		ro.put("goodsclassspeces", goodsclassspeces);
		ro.put("goodsspecs", goodsspecs);
		ro.put("currentgoodsspecs", currentgoodsspecs);
		ro.put("likegoodses", likegoodses);

		return ro;
	}
	
	// 活动商品查看
	@At("/sharelook")
	@Ok(">>:/goods/goods/look.action?id=${obj.id}")
	public Map sharelook(String info) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		Map map = myWxHelper.decrypt_info(info);
		
		String goodsid = (String)map.get("id");
		map.put("id", goodsid);
		return map;
	}

	// 商品浏览
	@At("/guestlike")
	@Ok("json")
	public Map guestlike(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> likegoodses = appgoodsService.guestlike(map);
		DynamicObject ro = new DynamicObject();
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
		DynamicObject ro = new DynamicObject();
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

		DynamicObject goods = appgoodsService.getgoodsbyspec(supgoodsid, specs);

		return goods;
	}
	
	// 商品浏览
	@At("/getphoto")
	@Ok("json")
	public Map getphoto(@Param("..") Map map) throws Exception
	{
		List<DynamicObject> goodsphotoes = appgoodsService.getphoto(map);
		Map ro = new DynamicObject();
		ro.put("goodsphotoes", goodsphotoes);
		return ro;
	}	
	
	// 活动商品查看
	@At("/eventlook")
	@Ok("->:/page/goods/goods/look.ftl")
	public Map eventlook(String info) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		Map map = myWxHelper.decrypt_info(info);
		
		String eventitemgoodsid = StringToolKit.formatText((String)map.get("eventitemgoodsid"));
		DynamicObject eventitemgoods = eventitemgoodsService.locate(eventitemgoodsid);
		
		String goodsid = eventitemgoods.getFormatAttr("goodsid");
		map.put("id", goodsid);
		
		return look(map);
	}
	
	protected Map jscfg() throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();
		String uri = myWxHelper.wx_uri(req);
		NutMap jscfg =  myWxHelper.wx_jsconfig(uri);
		return jscfg;
	}
	
	protected Map shareurl_look(String desc, String pic, Map map) throws Exception
	{
		HttpServletRequest req = Mvcs.getReq();

		String shareurl =  myWxHelper.wx_shareurl("goods/goods/sharelook.action", map);

		String title = VMallConstants.vmallname;
		String imgurl = ApiConfigKit.apiConfig.getServercontext() + "/" + pic;

		DynamicObject wxshare = new DynamicObject();
		wxshare.put("title", StringToolKit.formatText(title));
		wxshare.put("desc", StringToolKit.formatText(desc));
		wxshare.put("imgUrl", StringToolKit.formatText(imgurl));
		wxshare.put("shareurl", shareurl);
		
		return wxshare;
	}

}
