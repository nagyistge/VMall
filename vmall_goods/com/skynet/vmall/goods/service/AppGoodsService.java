package com.skynet.vmall.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.GoodsProductSpec;
import com.skynet.vmall.base.service.GoodsPriceService;
import com.skynet.vmall.base.service.GoodsService;

@InjectName("appgoodsService")
@IocBean(args =
{ "refer:dao" })
public class AppGoodsService extends SkynetDaoService
{
	public AppGoodsService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppGoodsService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	@Inject
	GoodsService goodsService;

	@Inject
	GoodsPriceService goodspriceService;

	// 浏览商品
	public List<DynamicObject> channel(Map map) throws Exception
	{
		String internal = (String) map.get("internal");
		String ctype = (String) map.get("ctype");
		String defspec = (String) map.get("defspec");

		String _orderby = (String) map.get("_orderby");
		String _order = StringToolKit.formatText((String) map.get("_order"), "asc");

		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods goods").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.classinternal like ").append(SQLParser.charLikeRightValue(internal)).append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(ctype))
		{
			sql.append("  and goods.ctype = ").append(SQLParser.charValue(ctype)).append("\n");
		}

		// 增加查询过滤条件
		if (!StringToolKit.isBlank(defspec))
		{
			sql.append("  and goods.defspec = ").append(SQLParser.charValue(defspec)).append("\n");
		}

		// 排序条件
		if (!StringToolKit.isBlank(_orderby))
		{
			sql.append("  order by ").append(_orderby);
			sql.append(" ").append(_order);
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

		String id = (String) map.get("id");
		DynamicObject sgoods = goodsService.locate(id);
		String internal = sgoods.getFormatAttr("classinternal");
		String saleprice = sgoods.getFormatAttr("saleprice");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and classinternal like ").append(SQLParser.charLikeRightValue(internal)).append("\n");
		sql.append("    and ctype = '货品' ").append("\n");
		sql.append("    and id <> ").append(SQLParser.charValue(id)).append("\n");

		if (!StringToolKit.isBlank(saleprice))
		{
			sql.append("    and saleprice < " + saleprice + " * 1.2 ").append("\n");
			sql.append("    and saleprice > " + saleprice + " * 0.8 ").append("\n");
		}
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

	// 查询指定规格的商品
	public DynamicObject getgoodsbyspec(String supgoodsid, List<ArrayList<String>> specs) throws Exception
	{
		StringBuffer sql = new StringBuffer();

		List<DynamicObject> goodses = goodsService.findByCond(Cnd.where("supid", "=", supgoodsid));

		String goodsid = new String();
		for (int i = 0; i < goodses.size(); i++)
		{
			int nums = 0;
			List<String> aspec = new ArrayList<String>();
			for (int j = 0; j < specs.size(); j++)
			{
				sql = new StringBuffer();
				aspec = specs.get(j);

				nums += sdao().count(GoodsProductSpec.class, Cnd.where("goodsid", "=", goodses.get(i).getFormatAttr("id")).and("specclass", "=", aspec.get(0)).and("spec", "=", aspec.get(1)));
			}
			if (nums == specs.size())
			{
				return goodses.get(i);
			}
		}

		return new DynamicObject();

	}

	public DynamicObject getPrice(Map map) throws Exception
	{
		String goodsid = StringToolKit.formatText((String) map.get("goodsid"));
		String memberid = StringToolKit.formatText((String) map.get("memberid"));
		String eventitemgoodsid = StringToolKit.formatText((String) map.get("eventitemgoodsid"));

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goodsprice goodsprice ");
		sql.append("  where 1 = 1 ");
		sql.append("    and goodsid = ").append(SQLParser.charValue(goodsid));

		if (StringToolKit.isBlank(memberid))
		{
			sql.append(" and goodsprice.membertype is null ");
		}

		if (StringToolKit.isBlank(eventitemgoodsid))
		{
			sql.append(" and goodsprice.eventitemgoodsid is null ");
		}
		else
		{
			sql.append(" and goodsprice.eventitemgoodsid = ").append(SQLParser.charValue(eventitemgoodsid));
		}

		// 如果没有任何限制条件，返回商品默认价格
		if (StringToolKit.isBlank(memberid) && StringToolKit.isBlank(eventitemgoodsid))
		{
			sql.append(" and goodsprice.isdef = '是' ");
		}

		DynamicObject goodsprice = goodspriceService.queryForMap(sql.toString());

		return goodsprice;
	}
}
