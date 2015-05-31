package com.skynet.vmall.goods.action;

import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.goods.service.GoodsService;

@IocBean
@At("/good/goods")
public class GoodsAction extends BaseAction
{
	@Inject
	private GoodsService goodsService;

	@At("/browse")
	@Ok("->:/page/goods/goodsclass/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Strings.sNull(page, "1"));
		map.put("_pagesize", Strings.sNull(pagesize, "5"));
		map.put("state", "新建");
		List<DynamicObject> reviews = goodsService.browse(map);
		ro.put("reviews", reviews);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}
}
