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

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.goods.service.GoodsClassService;
import com.skynet.vmall.goods.service.GoodsService;

@IocBean
@At("/mall/mall")
public class MallAction extends BaseAction
{
	@Inject
	private GoodsClassService goodsclassService;

	@Inject
	private GoodsService goodsService;

	@At("/index")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		String[] classes = new String[]
		{ "时尚女鞋", "流行男鞋", "面部护肤", "养生茶饮" };
		
		List<DynamicObject> goodsclasses = new ArrayList<DynamicObject>();
		for (int i = 0; i < classes.length; i++)
		{
			DynamicObject goodsclass = goodsclassService.locateBy(Cnd.where("cname", "=", classes[i]));
			List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", goodsclass.getFormatAttr("id")));
			goodsclass.setObj("subgoodsclasses", subgoodsclasses);
			goodsclasses.add(goodsclass);
		}
		ro.put("goodsclasses", goodsclasses);
		return ro;
	}
}
