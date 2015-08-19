package com.skynet.vmall.goods.service;

import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.GoodsClass;
import com.skynet.vmall.base.service.GoodsClassService;
import com.skynet.vmall.base.service.GoodsClassSpecService;
import com.skynet.vmall.base.service.GoodsClassSpecValueService;
import com.skynet.vmall.base.service.GoodsService;
import com.skynet.vmall.base.service.GoodsSpecService;
import com.skynet.vmall.base.service.GoodsSpecValueService;

@InjectName("appgoodsclassService")
@IocBean(args =
{ "refer:dao" })
public class AppGoodsClassService extends SkynetDaoService
{
	public static final int internal_len = 4;

	@Inject
	GoodsService goodsService;

	@Inject
	GoodsClassService goodsclassService;

	@Inject
	GoodsClassSpecService goodsclassspecService;

	@Inject
	GoodsClassSpecValueService goodsclassspecvalueService;

	@Inject
	GoodsSpecService goodsspecService;

	@Inject
	GoodsSpecValueService goodsspecvalueService;

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

		for (int i = 0; i < goodsclassspecs.size(); i++)
		{
			String specid = goodsclassspecs.get(i).getFormatAttr("id");
			List<DynamicObject> goodsclassspecvalues = goodsclassspecvalueService.findByCond(Cnd.where("goodsclassspecid", "=", specid));
			goodsclassspecs.get(i).setObj("specvalues", goodsclassspecvalues);
		}

		allspecs.addAll(goodsclassspecs);

		String supclassid = goodsclass.getSupid();
		if ("R0".equals(supclassid))
		{
		}
		else
		{
			get_all_spec(supclassid, allspecs);
		}

		return allspecs;
	}

	// 查询当前商品所具有的规格
	public List<DynamicObject> get_all_goods_spec(String goodsid, List<DynamicObject> allspecs) throws Exception
	{
		Goods goods = sdao().fetch(Goods.class, goodsid);

		List<DynamicObject> goodsspecs = goodsspecService.findByCond(Cnd.where("goodsid", "=", goodsid));

		for (int i = 0; i < goodsspecs.size(); i++)
		{
			String specid = goodsspecs.get(i).getFormatAttr("id");
			List<DynamicObject> goodsspecvalues = goodsspecvalueService.findByCond(Cnd.where("goodsspecid", "=", specid));
			goodsspecs.get(i).setObj("specvalues", goodsspecvalues);
		}

		allspecs.addAll(goodsspecs);

		return allspecs;
	}
	
	public List<DynamicObject> get_all_specvalue(String goodsid) throws Exception
	{
		Goods goods = goodsService.fetch(goodsid);
		String goodsclassid = goods.getClassid();
		GoodsClass subgoodsclass = goodsclassService.fetch(goodsclassid);
		String subinternal = subgoodsclass.getInternal();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select spec.specclass ").append("\n");
		sql.append("   from t_app_goodsclassspec spec, t_app_goodsclass goodsclass ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and spec.goodsclassid = goodsclass.id ").append("\n");
		sql.append("    and goodsclass.internal like concat("+SQLParser.charValue(subinternal)+", '%')").append("\n");
		sql.append("  union ").append("\n");
		sql.append(" select spec.specclass ").append("\n");
		sql.append("   from t_app_goodsspec spec ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and spec.goodsid = ").append(SQLParser.charValue(goodsid)).append("\n");
		sql.append("  order by specclass ").append("\n");
		

		List<DynamicObject> specclasses = sdao().queryForList(sql.toString());
		String specclass = new String();
		for(int i=0;i<specclasses.size();i++)
		{
			specclass = specclasses.get(i).getFormatAttr("specclass");
			sql = new StringBuffer();
			sql.append(" select specvalue.cvalue ").append("\n");
			sql.append("   from t_app_goodsclassspec spec, t_app_goodsclassspecval specvalue ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and spec.specclass = specvalue.specclass ").append("\n");
			sql.append("    and spec.specclass = " + SQLParser.charValue(specclass)).append("\n");
			sql.append("  union ").append("\n");
			sql.append(" select specvalue.cvalue ").append("\n");
			sql.append("   from t_app_goodsspec spec, t_app_goodsspecval specvalue ").append("\n");
			sql.append("  where 1 = 1 ").append("\n");
			sql.append("    and spec.specclass = specvalue.specclass ").append("\n");
			sql.append("    and spec.specclass = " + SQLParser.charValue(specclass)).append("\n");
			sql.append("    and spec.goodsid = " + SQLParser.charValue(goodsid)).append("\n");
			sql.append("  order by cvalue  ").append("\n");
			
			List<DynamicObject> specvalues = sdao().queryForList(sql.toString());
			specclasses.get(i).setObj("specvalues", specvalues);
		}
		return specclasses;
	}
	
	public List<DynamicObject> get_productspec(String goodsid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, cname, supid, saleprice, promoteprice, allstorenum, salenum, defspec ").append("\n");
		sql.append("   from t_app_goods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.supid = ").append(SQLParser.charValue(goodsid)).append("\n");
		List<DynamicObject> subgoodses = sdao().queryForList(sql.toString());
		
		for(int i=0;i<subgoodses.size();i++)
		{
			DynamicObject subgoods = subgoodses.get(i);
			String subgoodsid = subgoods.getFormatAttr("id");
			sql = new StringBuffer();
			sql.append(" select specclass, spec ");
			sql.append("   from t_app_goodspdspec pdspec");
			sql.append("  where 1 = 1 ");
			sql.append("    and goodsid = ").append(SQLParser.charValue(subgoodsid));
			
			List<DynamicObject> specvalues = sdao().queryForList(sql.toString());
			
			List<DynamicObject> tspecvalues = new ArrayList<DynamicObject>();
			for(int j=0;j<specvalues.size();j++)
			{
				DynamicObject atspecvalue = new DynamicObject();
				atspecvalue.put(specvalues.get(j).getFormatAttr("specclass"), specvalues.get(j).getFormatAttr("spec"));
				tspecvalues.add(atspecvalue);
			}
			
			subgoods.setObj("specvalues", tspecvalues);
		}
		
		return subgoodses;
	}
	
	

}
