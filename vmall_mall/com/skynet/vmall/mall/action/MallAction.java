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
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.filter.LogFilter;
import com.skynet.vmall.goods.service.GoodsClassService;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.wx.action.WXActionHelper;

@IocBean
@At("/mall/mall")
public class MallAction extends BaseAction
{
	@Inject
	WXActionHelper myWxHelper;
	
	@Inject
	private GoodsClassService goodsclassService;

	@Inject
	private GoodsService goodsService;

	@At("/index")
	@Ok("->:/page/mall/mall/index.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		// 系统微信配置信息
		HttpServletRequest req = Mvcs.getReq();
		HttpSession session = Mvcs.getHttpSession(true);
		String info = (String)session.getAttribute(GlobalConstants.sys_wxinfo);
		if(!StringToolKit.isBlank(info))
		{
			NutMap mapwx = myWxHelper.wx_minfo(info, req);
			ro.put("jscfg", mapwx.get("jscfg"));
			ro.put("shareurl", mapwx.get("shareurl"));
			ro.put("openid", mapwx.get("openid"));
			ro.put("recommender", mapwx.get("recommender"));		
		}
		
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
	
	// 非微信环境测试访问地址
	@At("/index_test")
	@Ok("->:/page/mall/mall/index_test.ftl")
	public Map index_test(@Param("..") Map map) throws Exception
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
