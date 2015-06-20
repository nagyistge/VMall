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

import com.skynet.framework.action.BaseAction;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.service.TagService;
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
	
	@Inject
	private TagService tagService;
	

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
		
		// 查询首页海报1级分类
		StringBuffer sql = new StringBuffer();
		sql.append(" select goodsclass.* ").append("\n");
		sql.append("   from t_app_goodsclass goodsclass, t_app_tag tag ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goodsclass.id = tag.objid ").append("\n");
		sql.append("    and tag.title = '首页海报1级分类' ").append("\n");
		sql.append("    and tag.objclass = 'GoodsClass' ").append("\n");	
		
		List<DynamicObject> goodsclasses =  goodsclassService.sdao().queryForList(sql.toString());

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
			
			// List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", goodsclass.getFormatAttr("id")));
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
		
		List<DynamicObject> goodsclasses =  goodsclassService.sdao().queryForList(sql.toString());

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
			
			// List<DynamicObject> subgoodsclasses = goodsclassService.findByCond(Cnd.where("supid", "=", goodsclass.getFormatAttr("id")));
			goodsclass.setObj("subpostgoodsclasses", subpostgoodsclasses);
			goodsclass.setObj("subgoodsclasses", subgoodsclasses);
		}

		ro.put("goodsclasses", goodsclasses);
		return ro;
	}
}
