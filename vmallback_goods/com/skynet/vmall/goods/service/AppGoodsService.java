package com.skynet.vmall.goods.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mapl.Mapl;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.GoodsClass;
import com.skynet.vmall.base.pojo.GoodsProductSpec;
import com.skynet.vmall.base.pojo.GoodsSpec;
import com.skynet.vmall.base.pojo.GoodsSpecValue;
import com.skynet.vmall.base.query.QueryHelper;

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

	public String insert(Map form, DynamicObject login_token) throws Exception
	{
		String id = UUIDGenerator.getInstance().getNextValue();
		String classid = (String) form.get("classid");
		GoodsClass goodsclass = sdao().fetch(GoodsClass.class, classid);
		String classinternal = goodsclass.getInternal();

		Goods goods = (Goods) Mapl.maplistToObj(form, Goods.class);
		goods.setId(id);
		goods.setClassid(classid);
		goods.setClassinternal(classinternal);
		goods.setCtype("商品");
		goods.setSalenum(new BigDecimal(0));

		sdao().insert(goods);

		return id;
	}

	public String update(Map form, DynamicObject login_token) throws Exception
	{
		Goods goods = (Goods) Mapl.maplistToObj(form, Goods.class);
		sdao().update(goods);

		// 清除之前的货品
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from t_app_goodspdspec ");
		sql.append("  where goodsid in (select id from t_app_goods where supid = " + SQLParser.charValue(goods.getId()) + ")");

		sdao().execute(Sqls.create(sql.toString()));

		sql = new StringBuffer();
		sql.append(" delete from t_app_goods where supid = " + SQLParser.charValue(goods.getId()));

		sdao().execute(Sqls.create(sql.toString()));

		String s = (String) form.get("specproducts");
		List<Map> products = (List<Map>) Json.fromJson(s);

		String[] pdsaleprice = (String[]) form.get("pdsaleprice");
		String[] pdpromoteprice = (String[]) form.get("pdpromoteprice");
		String[] pdallstorenum = (String[]) form.get("pdallstorenum");

		for (int i = 0; i < products.size(); i++)
		{
			Map product = products.get(i);
			System.out.println(product);
			List<Map> aspecs = (List<Map>) product.get("specvalues");

			// 新增货品
			Goods subgoods = Castors.me().castTo(Json.toJson(goods), Goods.class);

			subgoods.setId(UUIDGenerator.getInstance().getNextValue());
			subgoods.setSupid(goods.getId());
			subgoods.setCtype("货品");
			subgoods.setSaleprice(Types.parseBigDecimal(pdsaleprice[i], new BigDecimal(0)));
			subgoods.setPromoteprice(Types.parseBigDecimal(pdpromoteprice[i], new BigDecimal(0)));
			subgoods.setAllstorenum(Types.parseBigDecimal(pdallstorenum[i], new BigDecimal(0)));
			subgoods.setSalenum(new BigDecimal(0));
			sdao().insert(subgoods);

			// 新增货品规格
			for (int j = 0; j < aspecs.size(); j++)
			{
				Map aspec = aspecs.get(j);
				Iterator iter = aspec.keySet().iterator();
				while (iter.hasNext())
				{
					String specclass = (String) iter.next();
					String specvalue = (String) aspec.get(specclass);

					GoodsProductSpec spec = new GoodsProductSpec();
					spec.setId(UUIDGenerator.getInstance().getNextValue());
					spec.setGoodsid(subgoods.getId());
					spec.setSpecclass(specclass);
					spec.setSpec(specvalue);

					sdao().insert(spec);
				}
			}
		}

		return goods.getId();
	}

	// 上架
	public Map onsale(String goodsid) throws Exception
	{
		try
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" update t_app_goods set state = '上架' where id = ").append(SQLParser.charValue(goodsid));
			sdao().execute(Sqls.create(sql.toString()));

			sql = new StringBuffer();
			sql.append(" update t_app_goods set state = '上架' where supid = ").append(SQLParser.charValue(goodsid));
			sdao().execute(Sqls.create(sql.toString()));
		}
		catch (Exception e)
		{
			DynamicObject ro = new DynamicObject();
			ro.setAttr("state", "error");
			ro.setAttr("message", e.getMessage());
			return ro;
		}

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		return ro;

	}

	// 下架
	public Map offsale(String goodsid) throws Exception
	{
		try
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" update t_app_goods set state = '下架' where id = ").append(SQLParser.charValue(goodsid));
			sdao().execute(Sqls.create(sql.toString()));

			sql = new StringBuffer();
			sql.append(" update t_app_goods set state = '下架' where supid = ").append(SQLParser.charValue(goodsid));
			sdao().execute(Sqls.create(sql.toString()));
		}
		catch (Exception e)
		{
			DynamicObject ro = new DynamicObject();
			ro.setAttr("state", "error");
			ro.setAttr("message", e.getMessage());
			return ro;
		}

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		return ro;
	}
	
	// 设为缺省货品
	public Map defspec(String goodsid) throws Exception
	{
		try
		{
			Goods goods = sdao().fetch(Goods.class, goodsid);
			String supgoodsid = goods.getSupid();
			
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update t_app_goods set defspec = '否' where supid = ").append(SQLParser.charValue(supgoodsid));
			sdao().execute(Sqls.create(sql.toString()));

			sql = new StringBuffer();
			sql.append(" update t_app_goods set defspec = '是' where id = ").append(SQLParser.charValue(goodsid));
			sdao().execute(Sqls.create(sql.toString()));
		}
		catch (Exception e)
		{
			DynamicObject ro = new DynamicObject();
			ro.setAttr("state", "error");
			ro.setAttr("message", e.getMessage());
			return ro;
		}

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		return ro;
	}


	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = (Integer) map.get("_page");
		int pagesize = (Integer) map.get("_pagesize");

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		String classid = (String) map.get("classid");
		String cname = (String) map.get("cname");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(classid))
		{
			sql.append("  and classid = ").append(SQLParser.charValue(classid)).append("\n");
		}

		if (!StringToolKit.isBlank(cname))
		{
			sql.append("  and cname like ").append(SQLParser.charLikeRightValue(cname)).append("\n");
		}

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}

	// 添加规格
	public Map addspec(Map map) throws Exception
	{
		String goodsid = (String) map.get("goodsid");
		String specclass = (String) map.get("specclass");

		GoodsSpec goodsspec = new GoodsSpec();
		goodsspec.setId(UUIDGenerator.getInstance().getNextValue());
		goodsspec.setGoodsid(goodsid);
		goodsspec.setSno(1);
		goodsspec.setSpecclass(specclass);

		sdao().insert(goodsspec);

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setObj("goodsspec", goodsspec);
		return ro;
	}

	// 添加规格型号
	public Map addspecvalue(Map map) throws Exception
	{
		String goodsid = (String) map.get("goodsid");
		String specclass = (String) map.get("specclass");
		String spec = (String) map.get("spec");

		GoodsSpecValue goodsspecvalue = new GoodsSpecValue();
		goodsspecvalue.setId(UUIDGenerator.getInstance().getNextValue());
		goodsspecvalue.setGoodsid(goodsid);
		goodsspecvalue.setSno(1);
		goodsspecvalue.setSpecclass(specclass);
		goodsspecvalue.setCvalue(spec);

		sdao().insert(goodsspecvalue);

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setObj("goodsspecvalue", goodsspecvalue);
		return ro;
	}

}
