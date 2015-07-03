package com.skynet.vmall.goods.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.goods.service.AppGoodsService;

@IocBean
@At("/goods/goods")
public class GoodsAction
{
	
	@Inject
	private AppGoodsService appgoodsService;
	
	@At("/mainframe")
	@Ok("->:/page/goods/goods/mainframe.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		Map ro = new DynamicObject();
		return ro;
	}
	
	@At("/publish/selectgoodsclass")
	@Ok("->:/page/goods/goods/publish/selectgoodsclass.ftl")
	public Map selectgoodsclass(@Param("..") Map map) throws Exception
	{
		Map ro = new DynamicObject();
		
		return ro;
	}
	
	

	@At("/browse")
	@Ok("->:/page/goods/goods/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "5"));

		List<DynamicObject> goods = appgoodsService.browse(map);
	
		DynamicObject ro = new DynamicObject();

		ro.setAttr("cname", (String)map.get("cname"));
		ro.setAttr("classid", (String)map.get("classid"));		
		
		ro.put("goods", goods);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}



}
