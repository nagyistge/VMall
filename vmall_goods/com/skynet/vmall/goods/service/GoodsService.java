package com.skynet.vmall.goods.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.Goods;

@InjectName("goodsService")
@IocBean(args = { "refer:dao" }) 
public class GoodsService extends SkynetNameEntityService<Goods>
{
	public GoodsService()
	{
		super();
	}
	
	public GoodsService(Dao dao)
	{
		super(dao);
	}	
	
	public GoodsService(Dao dao, Class<Goods> entityType)
	{
		super(dao, entityType);
	}
	
	// 浏览商品
	public List<DynamicObject> channel(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String internal = (String)map.get("internal");
		String ctype = (String)map.get("ctype");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods goods").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.classinternal like ").append(SQLParser.charLikeRightValue(internal)).append("\n");
		// 增加查询过滤条件
		if(!StringToolKit.isBlank(ctype))
		{
			sql.append("  and goods.ctype = ").append(SQLParser.charValue(ctype)).append("\n");
		}
		
		// sql.append("  order by goods.cname ").append("\n");

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}	
	
	// 浏览商品
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	// 猜你喜欢商品
	public List<DynamicObject> guestlike(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String id = (String)map.get("id");
		DynamicObject sgoods = locate(id);
		String internal = sgoods.getFormatAttr("classinternal");
		String saleprice = sgoods.getFormatAttr("saleprice");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and classinternal like ").append(SQLParser.charLikeRightValue(internal));
		sql.append("    and saleprice < " + saleprice + " * 1.1 ");
		sql.append("    and saleprice > " + saleprice + " * 0.9 ");

		// 增加查询过滤条件
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	
	// 查询指定商品具有的规格
	public List<DynamicObject> findgoodsspec(String supgoodsid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select spec.spec, spec.specclass ").append("\n");
		sql.append("   from t_app_goods goods, t_app_goodsspec spec ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.id = ").append(SQLParser.charValue(supgoodsid)).append("\n");
		sql.append("    and goods.id = spec.goodsid ").append("\n");
	
		List<DynamicObject> specs = sdao().queryForList(sql.toString());

		return specs;
	}
	
	// 查询指定商品具有的规格
	public List<DynamicObject> findgoodsspec(String supgoodsid, String specclass) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select spec.spec, spec.specclass ").append("\n");
		sql.append("   from t_app_goods goods, t_app_goodsspec spec ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.id = ").append(SQLParser.charValue(supgoodsid)).append("\n");
		sql.append("    and goods.id = spec.goodsid ").append("\n");
		sql.append("    and goods.specclass = ").append(SQLParser.charValue(specclass)).append("\n");
	
		List<DynamicObject> specs = sdao().queryForList(sql.toString());

		return specs;
	}

}
