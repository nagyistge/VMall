package com.skynet.vmall.goods.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CheckSession;

import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsClassSpecService;
import com.skynet.vmall.base.service.GoodsClassSpecValueService;
import com.skynet.vmall.base.service.GoodsService;
import com.skynet.vmall.goods.service.AppGoodsClassService;
import com.skynet.vmall.goods.service.AppGoodsService;

@IocBean
@At("/goods/goods")
@Filters(
{ @By(type = CheckSession.class, args =
{ "sys_login_token", "/author/login/log.action" }) })
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
		List<DynamicObject> classes = goodsclassService.findByCond(Cnd.where("supid", "=", "R0").orderBy("internal", "ASC"));
		Map ro = new DynamicObject();
		ro.put("classes", classes);
		return ro;
	}

	@At("/getsubgoodsclass")
	@Ok("json")
	public List<DynamicObject> getsubgoodsclass(String supid) throws Exception
	{
		List<DynamicObject> classes = goodsclassService.findByCond(Cnd.where("supid", "=", supid).orderBy("internal", "ASC"));
		return classes;
	}

	@At("/browse")
	@Ok("->:/page/goods/goods/browse.ftl")
	public Map browse(@Param("..") Map map, @Param("_page") String page, @Param("_pagesize") String pagesize) throws Exception
	{
		map.put("_page", Types.parseInt(page, 1));
		map.put("_pagesize", Types.parseInt(pagesize, VMallConstants.pagesize));

		List<DynamicObject> goodses = appgoodsService.browse(map);
	
		DynamicObject ro = new DynamicObject();

		ro.put("goodses", goodses);
		ro.setAttr("state", (String)map.get("state"));
		
		ro.put("_page", map.get("_page"));
		ro.put("_pagesize", map.get("_pagesize"));
		ro.put("_maxpage", map.get("_maxpage"));
		ro.put("_startpage", map.get("_startpage"));
		ro.put("_endpage", map.get("_endpage"));

		return ro;
	}

	@At("/input")
	@Ok("->:/page/goods/goods/input.ftl")
	public Map input(@Param("..") Map map) throws Exception
	{
		String classid = (String) map.get("classid");
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

		trans_attr(new String[]
		{ "original_price,saleprice", "price,promoteprice", "basic_sales,basesalenum", "praise_num,basepraisenum", "num,allstorenum" }, map);
		trans_attr(new String[]
		{ "directly_money,rebate1", "superior_money,rebate2", "three_money,rebate3", "goods_no,code" }, map);

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

		List<DynamicObject> specvalues = appgoodsclassService.get_all_specvalue(id);
		List<DynamicObject> pdspecs = appgoodsclassService.get_productspec(id);

		Map ro = new DynamicObject();

		ro.put("goods", goods);
		ro.put("goodsclass", goodsclass);
		ro.put("specvalues", specvalues);
		ro.put("pdspecs", pdspecs);
		return ro;
	}

	@At("/update")
	@Ok(">>:/goods/goods/locate.action?id=${obj.id}")
	public Map update(@Param("..") Map map) throws Exception
	{
		HttpSession session = Mvcs.getHttpSession(true);
		DynamicObject login_token = (DynamicObject) session.getAttribute(GlobalConstants.sys_login_token);

		HttpServletRequest request = Mvcs.getReq();
		
		String[] pdsaleprice = request.getParameterValues("pdsaleprice");
		map.put("pdsaleprice", request.getParameterValues("pdsaleprice"));
		map.put("pdpromoteprice", request.getParameterValues("pdpromoteprice"));
		map.put("pdallstorenum", request.getParameterValues("pdallstorenum"));
		
		String id = appgoodsService.update(map, login_token);
		
		DynamicObject ro = new DynamicObject();

		ro.setAttr("id", id);

		return ro;
	}

	@At("/onsale")
	@Ok("json")
	public Map onsale(String goodsid) throws Exception
	{
		Map ro = new DynamicObject();
		ro = appgoodsService.onsale(goodsid);
		return ro;
	}

	@At("/offsale")
	@Ok("json")
	public Map offsale(String goodsid) throws Exception
	{
		Map ro = new DynamicObject();
		ro = appgoodsService.offsale(goodsid);
		return ro;
	}

	@At("/defspec")
	@Ok("json")
	public Map defspec(String goodsid) throws Exception
	{
		Map ro = new DynamicObject();
		ro = appgoodsService.defspec(goodsid);
		return ro;
	}

	
	@At("/getspecvalue")
	@Ok("json")
	public List<DynamicObject> getspecvalue(String goodsid) throws Exception
	{
		List<DynamicObject> specvalues = appgoodsclassService.get_all_specvalue(goodsid);
		return specvalues;
	}

	@At("/getpdspec")
	@Ok("json")
	public List<DynamicObject> getpdspec(String goodsid) throws Exception
	{
		List<DynamicObject> specvalues = appgoodsclassService.get_productspec(goodsid);
		return specvalues;
	}

	@At("/addspec")
	@Ok("json")
	public Map addspec(@Param("..") Map map) throws Exception
	{
		Map ro = appgoodsService.addspec(map);
		return ro;
	}

	@At("/addspecvalue")
	@Ok("json")
	public Map addspecvalue(@Param("..") Map map) throws Exception
	{
		Map ro = appgoodsService.addspecvalue(map);
		return ro;
	}
	
	@At("/selectphoto")
	@Ok("->:/page/goods/goods/selectphoto.ftl")
	public Map selectphoto(String goodsid) throws Exception
	{
		Map ro = new DynamicObject();
		ro.put("goodsid", goodsid);
		return ro;
	}
	
	@At("/getphoto")
	@Ok("json")
	public Map getphoto(String goodsid) throws Exception
	{
		Map ro = appgoodsService.getphoto(goodsid);
		return ro;
	}	
	
	@At("/updatephoto")
	@Ok("json")
	public Map updatephoto(@Param("..") Map map) throws Exception
	{
		Map ro = appgoodsService.updatephoto(map);
		return ro;
	}
	
	@At("/addphoto")
	@Ok("json")
	public Map addphoto(@Param("..") Map map) throws Exception
	{
		Map ro = appgoodsService.addphoto(map);
		return ro;
	}	

	@At("/leftmenu")
	@Ok("->:/page/goods/leftmenu.ftl")
	public Map lefmenu(String id) throws Exception
	{
		DynamicObject ro = new DynamicObject();
		return ro;
	}

	public Map trans_attr(String[] nameparis, Map map)
	{

		for (int i = 0; i < nameparis.length; i++)
		{
			String[] names = nameparis[i].split(",");
			map.put(names[1], map.get(names[0]));
		}
		return map;
	}

}
