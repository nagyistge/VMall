package com.skynet.vmall.goods.service;

import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.pojo.GoodsClass;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsClassSpecService;
import com.skynet.vmall.base.service.GoodsClassSpecValueService;

@InjectName("appgoodsclassService")
@IocBean(args =
{ "refer:dao" })
public class AppGoodsClassService extends SkynetDaoService
{
	public static final int internal_len = 4;
	
	@Inject
	GoodsClassService goodsclassService;
	
	@Inject
	GoodsClassSpecService goodsclassspecService;
	
	@Inject
	GoodsClassSpecValueService goodsclassspecvalueService;	

	public AppGoodsClassService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppGoodsClassService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}
	
	
	// 查询当前分类所具有的规格（上层分类至当前分类所有规格）
	public List<DynamicObject> get_all_spec(String classid, List<DynamicObject> allspecs) throws Exception
	{
		GoodsClass goodsclass = sdao().fetch(GoodsClass.class, classid);
		
		List<DynamicObject> goodsclassspecs = goodsclassspecService.findByCond(Cnd.where("goodsclassid", "=", classid));
		
		for(int i=0;i<goodsclassspecs.size();i++)
		{
			String specid = goodsclassspecs.get(i).getFormatAttr("id");
			List<DynamicObject> goodsclassspecvalues = goodsclassspecvalueService.findByCond(Cnd.where("goodsclassspecid", "=", specid));
			goodsclassspecs.get(i).setObj("specvalues", goodsclassspecvalues);
		}
		
		allspecs.addAll(goodsclassspecs);
		
		String supclassid = goodsclass.getSupid();
		if("R0".equals(supclassid))
		{
		}
		else
		{
			get_all_spec(supclassid, allspecs);
		}
		
		return allspecs;
	}
}
