package com.skynet.vmall.goods.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import com.skynet.framework.common.generator.RandomGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.GoodsClass;
import com.skynet.vmall.base.pojo.GoodsClassSpec;
import com.skynet.vmall.base.pojo.GoodsClassSpecValue;
import com.skynet.vmall.base.pojo.GoodsSpec;
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
		String cname = (String) form.get("cname");
		String classid = (String) form.get("classid");
		
		GoodsClass goodsclass = sdao().fetch(GoodsClass.class, classid);
		String classinternal = goodsclass.getInternal();

		Goods goods = new Goods();
		// 基本信息
		goods.setId(id);
		goods.setClassid(classid);
		goods.setClassinternal(classinternal);
		goods.setCname(cname);
		goods.setCtype("商品");
		goods.setSerial(Types.parseInt((String)form.get("serial"), 100));
		goods.setDealerid(""); // 维护人员所属机构
		goods.setDealername(""); // 维护人员所属机构
		goods.setCode((String)form.get("code"));
		goods.setBrand((String)form.get("brand"));	
		
		// 商品信息
		goods.setSaleprice(new BigDecimal((String)form.get("saleprice")));
		goods.setPromoteprice(new BigDecimal((String)form.get("promoteprice")));
		goods.setAllstorenum(Types.parseBigDecimal(((String)form.get("allstorenum")), new BigDecimal(0))); //num
		goods.setSalenum(new BigDecimal(0));
		goods.setBasesalenum(Types.parseBigDecimal((String)form.get("basesalenum"), new BigDecimal(RandomGenerator.getValue(3)))); //num
		goods.setPraisenum(0);
		goods.setBasepraisenum(Types.parseInt((String)form.get("basepraisenum"), RandomGenerator.getValue(3)));
		goods.setPopular(Types.parseInt((String)form.get("popular"), 0));
		goods.setBasepopular(Types.parseInt((String)form.get("basepopular"), 0));
		goods.setWeight(Types.parseBigDecimal((String)form.get("weight"), new BigDecimal(0)));
		goods.setVolume(Types.parseBigDecimal((String)form.get("volume"), new BigDecimal(0)));		
		
		// 佣金信息
		goods.setRebatetype((String)form.get("rebatetype"));
		goods.setRebate1(new BigDecimal((String)form.get("rebate1")));
		goods.setRebate2(new BigDecimal((String)form.get("rebate2")));
		goods.setRebate3(new BigDecimal((String)form.get("rebate3")));		
		
		// 物流信息
		goods.setIsfreelogistics((String)form.get("isfreelogistics")); // 是否免物流（是、否）
		goods.setFreightpayer((String)form.get("freightpayer"));// 运费设置（包邮、统一运费、运费模板）
		goods.setFullnummail(Types.parseInt((String)form.get("fullnummail"), 0));
		goods.setHidestock((String)form.get("hidestock"));
		goods.setQuota(Types.parseInt((String)form.get("quota"), 0));
		goods.setBuyneedpoints(Types.parseInt((String)form.get("buyneedpoints"), 0));
		goods.setConsumcoupon((String)form.get("consumcoupon"));
		goods.setConsumpoint(Types.parseInt((String)form.get("consumpoint"), 0));	
		goods.setJoinleveldiscount(Types.parseInt((String)form.get("joinleveldiscount"), 0));	
		
		sdao().insert(goods);
		
		// 根据选中规格新增商品规格
		String norms = (String) form.get("norms");
		if (!StringToolKit.isBlank(norms))
		{
			List sku_norms = (ArrayList)Json.fromJson(norms);
			for(int i=0;i<sku_norms.size();i++)
			{
				Map spec = (Map)sku_norms.get(i);
				List props = (ArrayList)spec.get("props");
				
				for(int j=0;j<props.size();j++)
				{
					String specvalueid = (String)props.get(j);
					GoodsClassSpecValue classspecvalue = sdao().fetch(GoodsClassSpecValue.class, specvalueid);
					String goodsclassspecid = classspecvalue.getGoodsclassspecid();
					GoodsClassSpec classspec = sdao().fetch(GoodsClassSpec.class, goodsclassspecid);
					
					GoodsSpec goodsspec = new GoodsSpec();
					goodsspec.setId(UUIDGenerator.getInstance().getNextValue());
					goodsspec.setGoodsid(id);
					goodsspec.setSpecclass(classspec.getSpecclass());
					goodsspec.setSpec(classspecvalue.getCvalue());
					sdao().insert(goodsspec);
				}
				
				System.out.println(props);
				
			}
			System.out.println(sku_norms);
		}

		// 根据规格清单新增货品
		String sku_props = (String) form.get("sku_props");
		
		if (!StringToolKit.isBlank(sku_props))
		{
			Map sku = (Map) Json.fromJson(sku_props);
			Object[] keys = sku.keySet().toArray();

			for (int i = 0; i < keys.length; i++)
			{
				Goods subgoods = new Goods();
				
				String subid = UUIDGenerator.getInstance().getNextValue();

				String akey = (String) keys[i];
				Map asku = (Map) sku.get(akey);
				BigDecimal o_price = new BigDecimal(String.valueOf(asku.get("o_price")));
				BigDecimal price = new BigDecimal(String.valueOf(asku.get("price")));
				BigDecimal stock = new BigDecimal(String.valueOf(asku.get("stock")));
				
				String defspec = "否";
				if(i==0)
				{
					defspec = "是";
				}
				// 基本信息
				subgoods.setSerial(Types.parseInt((String)form.get("serial"), 100));
				subgoods.setCode((String)form.get("code"));
				subgoods.setBrand((String)form.get("brand"));	
				
				// 商品信息
				subgoods.setSaleprice(new BigDecimal((String)form.get("saleprice")));
				subgoods.setPromoteprice(new BigDecimal((String)form.get("promoteprice")));
				
				subgoods.setAllstorenum(stock); // 货品库存数从明细规格库存数读取
				
				subgoods.setSalenum(new BigDecimal(0));
				subgoods.setBasesalenum(Types.parseBigDecimal((String)form.get("basesalenum"), new BigDecimal(RandomGenerator.getValue(3)))); //num
				subgoods.setPraisenum(0);
				subgoods.setBasepraisenum(Types.parseInt((String)form.get("basepraisenum"), RandomGenerator.getValue(3)));
				subgoods.setPopular(Types.parseInt((String)form.get("popular"), 0));
				subgoods.setBasepopular(Types.parseInt((String)form.get("basepopular"), 0));
				subgoods.setWeight(Types.parseBigDecimal((String)form.get("weight"), new BigDecimal(0)));
				subgoods.setVolume(Types.parseBigDecimal((String)form.get("volume"), new BigDecimal(0)));		
				
				// 佣金信息
				subgoods.setRebatetype((String)form.get("rebatetype"));
				subgoods.setRebate1(new BigDecimal((String)form.get("rebate1")));
				subgoods.setRebate2(new BigDecimal((String)form.get("rebate2")));
				subgoods.setRebate3(new BigDecimal((String)form.get("rebate3")));		
				
				// 物流信息
				subgoods.setIsfreelogistics((String)form.get("isfreelogistics")); // 是否免物流（是、否）
				subgoods.setFreightpayer((String)form.get("freightpayer"));// 运费设置（包邮、统一运费、运费模板）
				subgoods.setFullnummail(Types.parseInt((String)form.get("fullnummail"), 0));
				subgoods.setHidestock((String)form.get("hidestock"));
				subgoods.setQuota(Types.parseInt((String)form.get("quota"), 0));
				subgoods.setBuyneedpoints(Types.parseInt((String)form.get("buyneedpoints"), 0));
				subgoods.setConsumcoupon((String)form.get("consumcoupon"));
				subgoods.setConsumpoint(Types.parseInt((String)form.get("consumpoint"), 0));	
				subgoods.setJoinleveldiscount(Types.parseInt((String)form.get("joinleveldiscount"), 0));				

				subgoods.setId(subid);
				subgoods.setClassid(classid);
				subgoods.setClassinternal(classinternal);
				subgoods.setCname(cname);
				subgoods.setCtype("货品");
				subgoods.setSupid(id);
				subgoods.setSaleprice(o_price);
				subgoods.setPromoteprice(price);
				subgoods.setDefspec(defspec); // 
				
				sdao().insert(subgoods);

				String[] skukeys = akey.split(";");
				
				for (int j = 0; j < skukeys.length; j++)
				{
					GoodsClassSpecValue classspecvalue = sdao().fetch(GoodsClassSpecValue.class, skukeys[j]);
					String goodsclassspecid = classspecvalue.getGoodsclassspecid();
					GoodsClassSpec classspec = sdao().fetch(GoodsClassSpec.class, goodsclassspecid);
					
					// 新增货品规格
					GoodsSpec spec = new GoodsSpec();
					spec.setId(UUIDGenerator.getInstance().getNextValue());
					spec.setGoodsid(subid);
					spec.setSpecclass(classspec.getSpecclass());
					spec.setSpec(classspecvalue.getCvalue());
					
					sdao().insert(spec);
				}
			}

			System.out.println(sku);
		}

		return id;
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

}
