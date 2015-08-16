package com.skynet.vmall.goods.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.castor.Castors;
import org.nutz.dao.Cnd;
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
import com.skynet.vmall.base.pojo.Attach;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.GoodsClass;
import com.skynet.vmall.base.pojo.GoodsPhoto;
import com.skynet.vmall.base.pojo.GoodsPrice;
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
		goods.setState("新建");

		sdao().insert(goods);
		
		GoodsPrice goodsprice = new GoodsPrice();
		goodsprice.setId(UUIDGenerator.getInstance().getNextValue());
		goodsprice.setGoodsid(goods.getId());
		goodsprice.setCostprice(goods.getCostprice());
		goodsprice.setSaleprice(goods.getSaleprice());
		goodsprice.setPromoteprice(goods.getPromoteprice());
		goodsprice.setRebate1(goods.getRebate1());
		goodsprice.setRebate2(goods.getRebate2());
		goodsprice.setRebate3(goods.getRebate3());
		goodsprice.setRebate4(goods.getRebate4());
		goodsprice.setRebate5(goods.getRebate5());
		goodsprice.setIsdefault("是");
		
		sdao().insert(goodsprice);

		return id;
	}

	public String update(Map form, DynamicObject login_token) throws Exception
	{
		Goods goods = (Goods) Mapl.maplistToObj(form, Goods.class);
		sdao().update(goods);
		
		sdao().clear(GoodsPrice.class, Cnd.where("goodsid", "=", goods.getId()).andNot("isdefault", "=", "是"));
		
		GoodsPrice goodsprice = new GoodsPrice();
		goodsprice.setId(UUIDGenerator.getInstance().getNextValue());
		goodsprice.setGoodsid(goods.getId());
		goodsprice.setCostprice(goods.getCostprice());
		goodsprice.setSaleprice(goods.getSaleprice());
		goodsprice.setPromoteprice(goods.getPromoteprice());
		goodsprice.setRebate1(goods.getRebate1());
		goodsprice.setRebate2(goods.getRebate2());
		goodsprice.setRebate3(goods.getRebate3());
		goodsprice.setRebate4(goods.getRebate4());
		goodsprice.setRebate5(goods.getRebate5());
		goodsprice.setIsdefault("是");		

		sdao().insert(goodsprice);

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
			
			
			GoodsPrice subgoodsprice = new GoodsPrice();
			subgoodsprice.setId(UUIDGenerator.getInstance().getNextValue());
			subgoodsprice.setGoodsid(subgoods.getId());
			subgoodsprice.setCostprice(goods.getCostprice());
			subgoodsprice.setSaleprice(goods.getSaleprice());
			subgoodsprice.setPromoteprice(goods.getPromoteprice());
			subgoodsprice.setRebate1(goods.getRebate1());
			subgoodsprice.setRebate2(goods.getRebate2());
			subgoodsprice.setRebate3(goods.getRebate3());
			subgoodsprice.setRebate4(goods.getRebate4());
			subgoodsprice.setRebate5(goods.getRebate5());
			subgoodsprice.setIsdefault("是");		

			sdao().insert(subgoodsprice);

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


		String cname = (String) map.get("cname");
		String classname = (String) map.get("classname");
		String dealername = (String) map.get("dealername");
		String state = (String) map.get("state");

		StringBuffer sql = new StringBuffer();
		sql.append(" select goods.id, goods.cname, goodsclass.cname classname, goods.dealername, goods.saleprice, goods.salenum, goods.popular ").append("\n");
		sql.append("   from t_app_goods goods, t_app_goodsclass goodsclass ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.ctype = '商品' ").append("\n");
		sql.append("    and goods.classid = goodsclass.id ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(cname))
		{
			sql.append("  and cname like ").append(SQLParser.charLikeRightValue(cname)).append("\n");
		}
		
		if (!StringToolKit.isBlank(dealername))
		{
			sql.append("  and dealername like ").append(SQLParser.charLikeRightValue(state)).append("\n");
		}		
		
		if (!StringToolKit.isBlank(state))
		{
			sql.append("  and state = ").append(SQLParser.charValue(state)).append("\n");
		}

//		if (!StringToolKit.isBlank(classname))
//		{
//			sql.append("  and exists( ").append("\n");
//			sql.append("  select id from t_app_goodsclass goodsclass ").append("\n");
//			sql.append("   where goods.classinternal like concat(goodsclass.internal, '%') ").append("\n");
//			sql.append("     and goodsclass.cname like ").append(SQLParser.charLikeValue(classname)).append("\n");
//			sql.append("     )").append("\n");
//			sql.append(" ) ").append("\n");
//		}
		
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
	
	public Map updatephoto(Map map) throws Exception
	{
		String goodsid = (String) map.get("goodsid");
		String attachid = (String) map.get("attachid");
		String goodsphotoid = (String) map.get("goodsphotoid"); 
		String sno = (String) map.get("sno"); 
		String ctype = (String) map.get("ctype");
		
		Attach attach = sdao().fetch(Attach.class, attachid);
		String weburl = attach.getCurl() + "/" + attach.getFilename();
		
		GoodsPhoto goodsphoto = new GoodsPhoto();
		goodsphoto.setId(UUIDGenerator.getInstance().getNextValue());
		goodsphoto.setCtype(ctype);
		goodsphoto.setGoodsid(goodsid);

		if(!StringToolKit.isBlank(goodsphotoid))
		{
			goodsphoto = sdao().fetch(GoodsPhoto.class, goodsphotoid);
			goodsphoto.setUrl(weburl);
			sdao().update(goodsphoto);				
		}
		else
		{
			goodsphoto.setUrl(weburl);
			sdao().insert(goodsphoto);			
		}
		
		if("商品缩略图片".equals(ctype))
		{
			Goods goods = sdao().fetch(Goods.class, goodsid);
			goods.setPic(weburl);
			sdao().update(goods);			
		}

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setObj("goodsphoto", goodsphoto);
		return ro;
	}
	
	public Map addphoto(Map map) throws Exception
	{
		String goodsid = (String) map.get("goodsid");
		String ctype = (String) map.get("ctype");
		String url = (String) map.get("url");
		GoodsPhoto goodsphoto = new GoodsPhoto();
		goodsphoto.setId(UUIDGenerator.getInstance().getNextValue());
		goodsphoto.setCtype(ctype);
		goodsphoto.setGoodsid(goodsid);
		goodsphoto.setUrl(url);

		sdao().insert(goodsphoto);			

		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setObj("goodsphoto", goodsphoto);
		return ro;
	}	
	
	public Map getphoto(String goodsid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select vd.ctype, vd.ismulti, vd.ordernum, photo.id, photo.goodsid, photo.url pic, photo.sno ").append("\n");
		sql.append("   from ").append("\n");
		sql.append(" ( ").append("\n");
		sql.append(" select da.dvalue ctype, db.dvalue ismulti, da.ordernum ").append("\n");
		sql.append("   from t_sys_dictionary da, t_sys_dictionary db ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and da.dkey = 'app.goods.goodsphoto.ctype' ").append("\n");
		sql.append("    and db.dkey = 'app.goods.goodsphoto.ismulti' ").append("\n");
		sql.append("    and da.ordernum = db.ordernum ").append("\n");
		sql.append(" ) vd ").append("\n");
		sql.append("   left join t_app_goodsphoto photo ").append("\n");
		sql.append("     on vd.ctype = photo.ctype ").append("\n");
		sql.append("    and photo.goodsid = ").append(SQLParser.charValue(goodsid)).append("\n");
		sql.append("  order by ordernum ").append("\n");
		List<DynamicObject> photos = sdao().queryForList(sql.toString());
		
		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setObj("photos", photos);		

		return ro;
	}

}
