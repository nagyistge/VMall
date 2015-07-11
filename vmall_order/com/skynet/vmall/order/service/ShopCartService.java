package com.skynet.vmall.order.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.RandomGenerator;
import com.skynet.framework.common.generator.SNGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.pojo.OrderGoods;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;
import com.skynet.vmall.base.pojo.ShopCart;
import com.skynet.vmall.base.pojo.ShopCartGoods;
import com.skynet.vmall.base.service.EventItemGoodsService;
import com.skynet.vmall.base.service.EventItemService;
import com.skynet.vmall.base.service.EventService;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.member.service.MemberService;

@InjectName("shopcartService")
@IocBean(args = { "refer:dao" }) 
public class ShopCartService extends SkynetNameEntityService<ShopCart>
{
	@Inject
	EventService eventService;
	
	@Inject
	EventItemService eventitemService;
	
	@Inject
	EventItemGoodsService eventitemgoodsService;
	
	@Inject
	GoodsService goodsService;
	
	public ShopCartService()
	{
		super();
	}
	
	public ShopCartService(Dao dao)
	{
		super(dao);
	}	
	
	public ShopCartService(Dao dao, Class<ShopCart> entityType)
	{
		super(dao, entityType);
	}
	
	// 浏览商品
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String memberid = (String)map.get("memberid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select cartgoods.*, goods.pic goodspic").append("\n");
		sql.append("   from t_app_shopcartgoods cartgoods, t_app_shopcart cart, t_app_goods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and cart.memberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("    and cart.id = cartgoods.shopcartid ").append("\n");
		sql.append("    and goods.id = cartgoods.goodsid ").append("\n");
		
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	public Map addtocart(DynamicObject form, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String loginname = login_token.getFormatAttr(GlobalConstants.sys_login_user);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		
		String goodsid = StringToolKit.formatText((String)form.get("goodsid"));
		int nums = Types.parseInt(StringToolKit.formatText((String)form.get("nums")),1);
		String eventitemgoodsid = StringToolKit.formatText((String)form.get("eventitemgoodsid"));
		
		ShopCart cart = new ShopCart();
		// 检查是否已创建过购物车
		if(sdao().count(ShopCart.class, Cnd.where("id", "=", userid))==0)
		{
			cart.setId(userid);
			cart.setAmount(new BigDecimal(0));
			cart.setMemberid(userid);
			cart.setWxopenid(userwxopenid);
			sdao().insert(cart);
		}
		else
		{
			cart = sdao().fetch(ShopCart.class, userid);
		}
		
		Goods goods = sdao().fetch(Goods.class, goodsid);
		
		ShopCartGoods cartgoods = new ShopCartGoods();
		
		// 检查是否已经有同款商品，如有，增加数量，否则，新增购物商品;
		// 增加检查同款商品，活动不同，不合并
		int hasnums = sdao().count(ShopCartGoods.class, Cnd.where("shopcartid","=",cart.getId()).and("goodsid", "=", goodsid).and("eventitemgoodsid", "=", eventitemgoodsid));		
		if(hasnums>0)
		{
			cartgoods = sdao().fetch(ShopCartGoods.class, Cnd.where("shopcartid","=",cart.getId()).and("goodsid", "=", goodsid).and("eventitemgoodsid", "=", eventitemgoodsid));
			cartgoods.setNums(cartgoods.getNums()+nums);
			
			// 同款商品不同时间购物，如未提交付款，价格按照最新价格重新更新
			// 如果参加活动，获取活动价格
			if(StringToolKit.isBlank(eventitemgoodsid))
			{
				cartgoods.setSaleprice(goods.getSaleprice()); // 销售价（原价）
				cartgoods.setPromoteprice(goods.getPromoteprice()); // 促销价（现价）
			}
			else
			{
				DynamicObject goodsprice = goodsService.getPrice(new DynamicObject(new String[]{"goodsid","eventitemgoodsid"}, new String[]{goodsid, eventitemgoodsid}));
				cartgoods.setSaleprice(new BigDecimal(goodsprice.getFormatAttr("saleprice"))); // 销售价（原价）
				cartgoods.setPromoteprice(new BigDecimal(goodsprice.getFormatAttr("promoteprice"))); // 促销价（现价）
				cartgoods.setEventitemgoodsid(eventitemgoodsid);
			}
			
			BigDecimal amountsale = cartgoods.getSaleprice().multiply(new BigDecimal(cartgoods.getNums()));
			BigDecimal amountpromote = cartgoods.getPromoteprice().multiply(new BigDecimal(cartgoods.getNums()));	
			cartgoods.setAmountsale(amountsale);
			cartgoods.setAmountpromote(amountpromote);
			
			sdao().update(cartgoods);
		}
		else
		{
			cartgoods.setId(UUIDGenerator.getInstance().getNextValue());
			cartgoods.setShopcartid(cart.getId());

			cartgoods.setMemberid(""); // 商品经销商会员标识
			cartgoods.setWxopenid(""); // 商品经销商会员微信标识
			cartgoods.setDealerid(goods.getDealerid());// 商家标识（组织机构）
			cartgoods.setDealername(goods.getDealername()); // 商家名称	
			
			cartgoods.setGoodsid(goodsid);
			cartgoods.setGoodsname(goods.getCname());
			cartgoods.setNums(nums);	
			
			// 同款商品不同时间购物，如未提交付款，价格按照最新价格重新更新
			// 如果参加活动，获取活动价格
			if(StringToolKit.isBlank(eventitemgoodsid))
			{
				cartgoods.setSaleprice(goods.getSaleprice()); // 销售价（原价）
				cartgoods.setPromoteprice(goods.getPromoteprice()); // 促销价（现价）
			}
			else
			{
				DynamicObject goodsprice = goodsService.getPrice(new DynamicObject(new String[]{"goodsid","eventitemgoodsid"}, new String[]{goodsid, eventitemgoodsid}));
				cartgoods.setSaleprice(new BigDecimal(goodsprice.getFormatAttr("saleprice"))); // 销售价（原价）
				cartgoods.setPromoteprice(new BigDecimal(goodsprice.getFormatAttr("promoteprice"))); // 促销价（现价）
				cartgoods.setEventitemgoodsid(eventitemgoodsid);
			}
			
			BigDecimal amountsale = cartgoods.getSaleprice().multiply(new BigDecimal(cartgoods.getNums()));
			BigDecimal amountpromote = cartgoods.getPromoteprice().multiply(new BigDecimal(cartgoods.getNums()));	
			cartgoods.setAmountsale(amountsale);
			cartgoods.setAmountpromote(amountpromote);
			
			sdao().insert(cartgoods);
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(cartgoods.nums) nums from t_app_shopcartgoods cartgoods, t_app_shopcart cart ");
		sql.append(" where 1 = 1 ");
		sql.append("   and cartgoods.shopcartid = cart.id ");
		sql.append("   and cart.memberid = ").append(SQLParser.charValue(userid));
		
		int cartgoodsnums = Types.parseInt(sdao().queryForMap(sql.toString()).getFormatAttr("nums"), 0);
				
		Map map = new DynamicObject();
		map.put("state", "success");
		map.put("shopcart", cart);
		map.put("cartgoodsnum", cartgoodsnums);
		return map;
	}
	
	// 购物车结算。
	public Map placeorder(DynamicObject form, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		// 当前用户姓名未填写，不允许下单
//		if(StringToolKit.isBlank(username))
//		{
//			Map remap = new DynamicObject();
//			remap.put("state", "error");
//			remap.put("message", "亲，我们还不知道你的姓名，无法下单，快去个人中心填写你的资料吧。");
//			return remap;
//		}
		
		// 购物车
		List<String> ids = (List<String>)form.get("ids");
		List<String> numses = (List<String>)form.get("numses");
		
		if(ids.size()==0)
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "亲，你的购物车里面好像没有拍好的宝贝，再看看吧。");
			return remap;			
		}
		
		// 检查购物车商品的厂商，按照不同的厂商产生订单
		Map<String, List<DynamicObject>> deals = new DynamicObject();
		for (int i = 0; i < ids.size(); i++)
		{
			int nums = Types.parseInt(numses.get(i), 0);
			// 如果购买数量不大于0，不加入购买订单。
			if(nums<=0)
			{
				continue;
			}
			
			String id = ids.get(i);
			DynamicObject shopcartgoods = sdao().locateBy("t_app_shopcartgoods", Cnd.where("id", "=", id));
			
			if(StringToolKit.isBlank(shopcartgoods.getFormatAttr("id")))
			{
				// continue;
				Map remap = new DynamicObject();
				remap.put("state", "error");
				remap.put("message", "亲，当前购物车里的宝贝已经无效，请刷新后重试。");
				return remap;
			}
			
			shopcartgoods.setAttr("nums", nums);
			
			String goodsid = shopcartgoods.getFormatAttr("goodsid");
			DynamicObject goods = sdao().locateBy("t_app_goods", Cnd.where("id", "=", goodsid));
			String dealid = goods.getFormatAttr("dealerid");
			if(deals.containsKey(dealid))
			{
				List<DynamicObject> dealcartgoodses = deals.get(dealid);
				
				dealcartgoodses.add(shopcartgoods);
			}
			else
			{
				List<DynamicObject> dealcartgoodses = new ArrayList<DynamicObject>();
				dealcartgoodses.add(shopcartgoods);
				deals.put(dealid, dealcartgoodses);
			}
		}
		
		String batchno = String.valueOf(RandomGenerator.getValue(8));
		Iterator<String> keys = deals.keySet().iterator();
		List<String> orderids = new ArrayList<String>();
		while(keys.hasNext())
		{
			String dealid = keys.next();
			List<DynamicObject> shopcartgoodses = deals.get(dealid);
			orderids.add(makeorder(dealid, batchno, shopcartgoodses, login_token));
		}

		Map remap = new DynamicObject();
		remap.put("state", "success");
		remap.put("batchno", batchno);
		remap.put("ids", orderids);
		
		return remap;
	}
	
	// 根据厂商及商品列表创建订单
	protected String makeorder(String dealid, String batchno, List<DynamicObject> shopcartgoodses, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		Member member = sdao().fetch(Member.class, userid);
		
		Order order = new Order();

		// 基本信息
		String orderid = UUIDGenerator.getInstance().getNextValue();		
		order.setId(orderid);
		order.setSellerid(dealid);
		
		// 购买人信息

		order.setMemberid(userid);
		order.setWxopenid(userwxopenid);
		order.setMembercname(username);
		order.setMembercno(member.getCno());
		order.setPhone(member.getPhone());
		// 收货人信息
		order.setTakercname(member.getCname());
		order.setTakermobile(member.getPhone());		
		order.setTakeprovince(member.getProvince());
		order.setTakecity(member.getCity());
		order.setTakecounty(member.getCounty());
		order.setTaketown(member.getTown());
		order.setTakepostcode(member.getPostcode());
		order.setTakeaddress(member.getAddr());
		// 订单基本信息
		order.setCno(SNGenerator.getValue(8));
		order.setBatchno(batchno);
		order.setOrdertime(new Timestamp(System.currentTimeMillis()));
		order.setState("下单");
		order.setPaystate("未支付");
		sdao().insert(order);
		
		for(int i=0;i<shopcartgoodses.size();i++)
		{
			// 再次检查数量，防止非法数量；	
			int nums = Types.parseInt(shopcartgoodses.get(i).getFormatAttr("nums"),0);
			if(nums<=0)
			{
				continue;
			}
			
			// 生成订单明细
			String cartgoodsid = shopcartgoodses.get(i).getFormatAttr("id");
			ShopCartGoods cartgoods = sdao().fetch(ShopCartGoods.class, cartgoodsid);
			Goods goods = sdao().fetch(Goods.class, cartgoods.getGoodsid());
			String goodsclassid = goods.getClassid();
			
			MemberService memberService = new MemberService(sdao(), Member.class);
			List<DynamicObject> supmembers = memberService.findsupmembers(userid, MemberService.level_rebate);

			// 生成订单商品记录
			OrderGoods ordergoods = new OrderGoods();
			String ordergoodsid = UUIDGenerator.getInstance().getNextValue();
			ordergoods.setId(ordergoodsid);
			ordergoods.setOrderid(orderid);
			ordergoods.setMemberid(userid);
			ordergoods.setWxopenid(userwxopenid);
			ordergoods.setSellerid(dealid);
			ordergoods.setState("下单");
			
			ordergoods.setGoodsid(cartgoods.getGoodsid());
			ordergoods.setGoodsname(cartgoods.getGoodsname());

			ordergoods.setNums(nums);
			ordergoods.setEventitemgoodsid(cartgoods.getEventitemgoodsid());
			
			// 单价、金额应为下订单时的商品实时价格
//			ordergoods.setSaleprice(goods.getSaleprice());
//			ordergoods.setPromoteprice(goods.getPromoteprice());
//			ordergoods.setRealprice(goods.getPromoteprice());
//
//			BigDecimal amountsale = goods.getSaleprice().multiply(new BigDecimal(ordergoods.getNums()));
//			BigDecimal amountpromote = goods.getPromoteprice().multiply(new BigDecimal(ordergoods.getNums()));
//			BigDecimal amountreal = goods.getPromoteprice().multiply(new BigDecimal(ordergoods.getNums()));				
//			
//			ordergoods.setAmountsale(amountsale);
//			ordergoods.setAmountpromote(amountpromote);
//			ordergoods.setAmountreal(amountreal);
//			
//			for(int j=0;j<supmembers.size();j++)
//			{
//				DynamicObject supmember = supmembers.get(j);
//				int level = j + 1;
//				
//				Method mog_setrebate = OrderGoods.class.getMethod("setRebate"+level, BigDecimal.class);
//				Method mog_setsupwxopenid = OrderGoods.class.getMethod("setSupwxopenid"+level, String.class);
//				Method mog_setsupmemberid = OrderGoods.class.getMethod("setSupmemberid"+level, String.class);
//				
//				Method mg = Goods.class.getMethod("getRebate"+level);
//				mog_setrebate.invoke(ordergoods, mg.invoke(goods));
//				mog_setsupwxopenid.invoke(ordergoods, supmember.getFormatAttr("wxopenid"));
//				mog_setsupmemberid.invoke(ordergoods, supmember.getFormatAttr("id"));
//			}
//			
			sdao().insert(ordergoods); 
//
//			for(int j=0;j<supmembers.size();j++)
//			{
//				String supmemberid = supmembers.get(j).getFormatAttr("id");
//				String supwxopenid = supmembers.get(j).getFormatAttr("wxopenid");
//				String supmembercname = supmembers.get(j).getFormatAttr("cname");
//				int level = j + 1;
//				
//				// 生成订单商品返利记录
//				OrderGoodsRebate orderrebate = new OrderGoodsRebate();
//				String orderrebateid = UUIDGenerator.getInstance().getNextValue();			
//				orderrebate.setId(orderrebateid);
//				orderrebate.setOrdercno(order.getCno());
//				orderrebate.setOrdergoodsid(ordergoodsid);
//				orderrebate.setOrdergoodsname(cartgoods.getGoodsname());
//				orderrebate.setSupmemberid(supmemberid);
//				orderrebate.setSupwxopenid(supwxopenid);
//				orderrebate.setSupmembercname(supmembercname);
//				orderrebate.setSubmemberid(userid);
//				orderrebate.setSubwxopenid(userwxopenid);
//				orderrebate.setSubmembercname(username);
//				orderrebate.setLevel(level);
//				
//				Method m = Goods.class.getMethod("getRebate"+level);
//				orderrebate.setRebate((BigDecimal)m.invoke(goods));
//				orderrebate.setNums(ordergoods.getNums());
//				orderrebate.setScore(orderrebate.getRebate().multiply(new BigDecimal(ordergoods.getNums())));
//				orderrebate.setRebatetime(new Timestamp(System.currentTimeMillis()));
//				sdao().insert(orderrebate);
//			}
			
			// 清除购物车商品
			sdao().delete(cartgoods);
		}
		
		// 订单合计信息
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select sum(saleprice * nums) amountsale, sum(promoteprice * nums) amountpromote, sum(realprice * nums) amount ");
//		sql.append("   from t_app_ordergoods goods ").append("\n");
//		sql.append("  where 1 = 1 ").append("\n");
//		sql.append("    and goods.orderid = ").append(SQLParser.charValue(orderid)).append("\n");
//		
//		DynamicObject amounts = sdao().queryForMap(sql.toString());
//		BigDecimal amountsale_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amountsale"), 0));
//		BigDecimal amountpromote_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amountpromote"), 0));
//		BigDecimal amount_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amount"), 0));
//		order.setAmountsale(amountsale_all);
//		order.setAmountpromote(amountpromote_all);
//		order.setAmount(amount_all);
//		sdao().update(order);	
		
		return order.getId();
	}
	
	// 抢购订单结算
	public Map rushorder(DynamicObject form, DynamicObject login_token) throws Exception
	{

		
		Map remap = new DynamicObject();
		remap.put("state", "success");
		
		return remap;
	}

}
