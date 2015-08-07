package com.skynet.vmall.mall.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.blue.wxmp.sdk.api.WxApi;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.service.GoodsService;

@IocBean
@At("/mall/mall")
public class MallAction
{
	@Inject
	WxApi myWxApi;

	@Inject
	GoodsService goodsService;

	@At("/ajaxmenu")
	@Ok("json")
	public List ajaxmenu(@Param("..") Map map) throws Exception
	{
		System.out.println(map);
		String key = StringToolKit.formatText((String) map.get("key"));

		List<DynamicObject> datas = goodsService.findByCond(Cnd.where("ctype", "=", "货品"));
		int nums = datas.size();
		for (int i = 0; i < nums; i++)
		{
			DynamicObject obj = datas.get(i);
			obj.setAttr("link", "/goods/goods/look.action?id=" + obj.getFormatAttr("id"));
			obj.setAttr("urlview", "");
			obj.setAttr("title", obj.getFormatAttr("cname"));
			obj.setAttr("file_path", obj.getFormatAttr("pic"));
			obj.setAttr("price", obj.getFormatAttr("promoteprice"));
			datas.add(obj);
		}

		return datas;
	}

}
