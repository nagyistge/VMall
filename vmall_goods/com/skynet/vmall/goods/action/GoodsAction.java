package com.skynet.vmall.goods.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.goods.service.GoodsClassService;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.member.service.MemberService;

@IocBean
@At("/goods/goods")
public class GoodsAction extends BaseAction
{
	@Inject
	private MemberService memberService;

	@Inject
	private GoodsClassService goodsclassService;

	@Inject
	private GoodsService goodsService;

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
	public Map channel(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		String classid = (String) map.get("classid");
		DynamicObject goodsclass = goodsclassService.locate(classid);

		DynamicObject supgoodsclass = goodsclassService.locateBy(Cnd.where("id", "=", goodsclass.getFormatAttr("supid")));

		List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", classid));

		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "10"));
		List<DynamicObject> goods = goodsService.channel(map);

		ro.put("supgoodsclass", supgoodsclass);
		ro.put("goodsclass", goodsclass);
		ro.put("subgoodsclasses", subgoodsclasses);
		ro.put("goods", goods);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
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
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);

		String id = (String) map.get("id");
		DynamicObject goods = goodsService.locate(id);

		ro.put("member", member);
		ro.put("goods", goods);
		return ro;
	}
	
	
	// 商品详情
	@At("/detail")
	@Ok("->:/page/goods/goods/detail.ftl")
	public Map detail(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		DynamicObject member = memberService.locate(userid);

		String id = (String) map.get("id");
		DynamicObject goods = goodsService.locate(id);

		ro.put("member", member);
		ro.put("goods", goods);
		return ro;
	}

}
