package com.skynet.vmall.goods.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsClassSpecService;
import com.skynet.vmall.base.service.GoodsClassSpecValueService;
import com.skynet.vmall.base.service.GoodsService;
import com.skynet.vmall.goods.service.AppGoodsClassService;
import com.skynet.vmall.goods.service.AppGoodsService;

@IocBean
@At("/goods/goods")
public class GoodsAction
{
	@Inject
	private GoodsClassService goodsclassService;
	
	@Inject
	private GoodsClassSpecService goodsclassspecService;	

	@Inject
	private GoodsClassSpecValueService goodsclassspecvalueService;	

	@Inject
	private GoodsService goodsService;
	
	@Inject
	private AppGoodsService appgoodsService;
	
	@Inject
	private AppGoodsClassService appgoodsclassService;	
	
	@At("/mainframe")
	@Ok("->:/page/goods/goods/mainframe.ftl")
	public Map index(@Param("..") Map map) throws Exception
	{
		Map ro = new DynamicObject();
		return ro;
	}
	
	@At("/selectgoodsclass")
	@Ok("->:/page/goods/goods/selectgoodsclass.ftl")
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

		ro.put("goods", goods);
		ro.put("_page", page);
		ro.put("_pagesize", pagesize);

		return ro;
	}

	@At("/input")
	@Ok("->:/page/goods/goods/input.ftl")
	public Map input(@Param("..") Map map) throws Exception
	{
		String classid = (String)map.get("classid");
		DynamicObject goodsclass = goodsclassService.locate(classid);
		List<DynamicObject> goodsclassspecs = appgoodsclassService.get_all_spec(classid, new ArrayList<DynamicObject>());
		
		Map ro = new DynamicObject();
		
		ro.put("goodsclass", goodsclass);
		ro.put("goodsclassspecs", goodsclassspecs);
		return ro;
	}
	
	@At("/insert")
	@Ok(">>:/goods/goods/locate.action?id=${obj.id}")
	public Map insert(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		trans_attr(new String[]{"original_price,saleprice","price,promoteprice","basic_sales,basesalenum","praise_num,basepraisenum","num,allstorenum"}, map);
		trans_attr(new String[]{"directly_money,rebate1","superior_money,rebate2","three_money,rebate3","goods_no,code"}, map);
		
//		map.put("saleprice", map.get("original_price"));	
//		map.put("promoteprice", map.get("price"));
//		map.put("basesalenum", map.get("basic_sales"));		
//		map.put("basepraisenum", map.get("praise_num"));			
//		map.put("allstorenum", map.get("num")); //总库存		
//		map.put("rebate1", map.get("directly_money"));
//		map.put("rebate2", map.get("superior_money"));
//		map.put("rebate3", map.get("three_money"));
//		
//		map.put("code", "goods_no");
		
		
		String id = appgoodsService.insert(map, login_token);
		
		DynamicObject ro = new DynamicObject();
		
		ro.setAttr("id", id);
		
		return ro;
	}
	
	@At("/locate")
	@Ok("->:/page/goods/goods/locate.ftl")
	public Map locate(String id) throws Exception
	{
		
		DynamicObject goods = goodsService.locate(id);
		String classid = goods.getFormatAttr("classid");
		DynamicObject goodsclass = goodsclassService.locate(classid);
		List<DynamicObject> goodsclassspecs = appgoodsclassService.get_all_spec(classid, new ArrayList<DynamicObject>());
		
		Map ro = new DynamicObject();
		
		ro.put("goods", goods);
		ro.put("goodsclass", goodsclass);
		ro.put("goodsclassspecs", goodsclassspecs);
		return ro;
	}	
	
	public Map trans_attr(String[]nameparis, Map map)
	{

		for(int i=0;i<nameparis.length;i++)
		{
			String[] names = nameparis[i].split(",");
			map.put(names[1], map.get(names[0]));
		}
		return map;
	}

}
