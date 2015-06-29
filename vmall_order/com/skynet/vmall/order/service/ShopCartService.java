package com.skynet.vmall.order.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

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
import com.skynet.vmall.member.service.MemberService;

@InjectName("shopcartService")
@IocBean(args = { "refer:dao" }) 
public class ShopCartService extends SkynetNameEntityService<ShopCart>
{
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
		
		String goodsid = (String)form.get("goodsid");
		int nums = Types.parseInt((String)form.get("nums"),1);
		
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
		
		//检查是否已经有同款商品，如有，增加数量，否则，新增购物商品;
		if(sdao().count(ShopCartGoods.class, Cnd.where("shopcartid","=",cart.getId()).and("goodsid", "=", goodsid))>0)
		{
			cartgoods = sdao().fetch(ShopCartGoods.class, Cnd.where("shopcartid","=",cart.getId()).and("goodsid", "=", goodsid));
			cartgoods.setNums(cartgoods.getNums()+nums);
			
			// 同款商品不同时间购物，如未提交付款，价格按照最新价格重新更新
			cartgoods.setSaleprice(goods.getSaleprice()); // 销售价（原价）
			cartgoods.setPromoteprice(goods.getPromoteprice()); // 促销价（促销价）
			
			BigDecimal amountsale = goods.getSaleprice().multiply(new BigDecimal(cartgoods.getNums()));
			BigDecimal amountpromote = goods.getPromoteprice().multiply(new BigDecimal(cartgoods.getNums()));
			
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
			cartgoods.setGoodsid(goodsid);
			cartgoods.setGoodsname(goods.getCname());
			cartgoods.setNums(nums);			
			cartgoods.setSaleprice(goods.getSaleprice()); // 销售价（原价）
			cartgoods.setPromoteprice(goods.getPromoteprice()); // 促销价（促销价）
			
			BigDecimal amountsale = goods.getSaleprice().multiply(new BigDecimal(cartgoods.getNums()));
			BigDecimal amountpromote = goods.getPromoteprice().multiply(new BigDecimal(cartgoods.getNums()));
			
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
		// 
		List<DynamicObject> shopcartgoodses = new ArrayList<DynamicObject>();
		for (int i = 0; i < ids.size(); i++)
		{
			String id = ids.get(i);
			int nums = Types.parseInt(numses.get(i), 0);
			
			if (StringToolKit.isBlank(id))
			{
				continue;
			}
			
			// 如果购买数量不大于0，不加入购买订单。
			if(nums<=0)
			{
				continue;
			}			
			
			DynamicObject shopcartgoods = sdao().locateBy("t_app_shopcartgoods", Cnd.where("id", "=", id));
			if(StringToolKit.isBlank(shopcartgoods.getFormatAttr("id")))
			{
				continue;
			}
			
			shopcartgoods.setAttr("nums", nums);
			
			shopcartgoodses.add(shopcartgoods);
		}
		
		Member member = sdao().fetch(Member.class, userid);
		
		Order order = new Order();
		String orderid = UUIDGenerator.getInstance().getNextValue();
		// 购买人信息
		order.setId(orderid);
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
			ordergoods.setGoodsid(cartgoods.getGoodsid());
			ordergoods.setGoodsname(cartgoods.getGoodsname());

			ordergoods.setNums(nums);
			ordergoods.setSaleprice(goods.getSaleprice());
			ordergoods.setPromoteprice(goods.getPromoteprice());
			ordergoods.setRealprice(goods.getPromoteprice());
			ordergoods.setState("下单");
			
			BigDecimal amountsale = goods.getSaleprice().multiply(new BigDecimal(ordergoods.getNums()));
			BigDecimal amountpromote = goods.getPromoteprice().multiply(new BigDecimal(ordergoods.getNums()));
			BigDecimal amountreal = goods.getPromoteprice().multiply(new BigDecimal(ordergoods.getNums()));				
			
			ordergoods.setAmountsale(amountsale);
			ordergoods.setAmountpromote(amountpromote);
			ordergoods.setAmountreal(amountreal);
			
			for(int j=0;j<supmembers.size();j++)
			{
				DynamicObject supmember = supmembers.get(j);
				int level = j + 1;
				
				Method mog_setrebate = OrderGoods.class.getMethod("setRebate"+level, BigDecimal.class);
				Method mog_setsupwxopenid = OrderGoods.class.getMethod("setSupwxopenid"+level, String.class);
				Method mog_setsupmemberid = OrderGoods.class.getMethod("setSupmemberid"+level, String.class);
				
				Method mg = Goods.class.getMethod("getRebate"+level);
				mog_setrebate.invoke(ordergoods, mg.invoke(goods));
				mog_setsupwxopenid.invoke(ordergoods, supmember.getFormatAttr("wxopenid"));
				mog_setsupmemberid.invoke(ordergoods, supmember.getFormatAttr("id"));
			}
			
			sdao().insert(ordergoods); 

			for(int j=0;j<supmembers.size();j++)
			{
				String supmemberid = supmembers.get(j).getFormatAttr("id");
				String supwxopenid = supmembers.get(j).getFormatAttr("wxopenid");
				String supmembercname = supmembers.get(j).getFormatAttr("cname");
				int level = j + 1;
				
				// 生成订单商品返利记录
				OrderGoodsRebate orderrebate = new OrderGoodsRebate();
				String orderrebateid = UUIDGenerator.getInstance().getNextValue();			
				orderrebate.setId(orderrebateid);
				orderrebate.setOrdercno(order.getCno());
				orderrebate.setOrdergoodsid(ordergoodsid);
				orderrebate.setOrdergoodsname(cartgoods.getGoodsname());
				orderrebate.setSupmemberid(supmemberid);
				orderrebate.setSupwxopenid(supwxopenid);
				orderrebate.setSupmembercname(supmembercname);
				orderrebate.setSubmemberid(userid);
				orderrebate.setSubwxopenid(userwxopenid);
				orderrebate.setSubmembercname(username);
				orderrebate.setLevel(level);
				
				Method m = Goods.class.getMethod("getRebate"+level);
				orderrebate.setRebate((BigDecimal)m.invoke(goods));
				orderrebate.setNums(ordergoods.getNums());
				orderrebate.setScore(orderrebate.getRebate().multiply(new BigDecimal(ordergoods.getNums())));
				orderrebate.setRebatetime(new Timestamp(System.currentTimeMillis()));
				sdao().insert(orderrebate);
			}
			
			// 清除购物车商品
			sdao().delete(cartgoods);
		}
		
		// 订单合计信息
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(saleprice * nums) amountsale, sum(promoteprice * nums) amountpromote, sum(realprice * nums) amount ");
		sql.append("   from t_app_ordergoods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and goods.orderid = ").append(SQLParser.charValue(orderid)).append("\n");
		
		DynamicObject amounts = sdao().queryForMap(sql.toString());
		BigDecimal amountsale_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amountsale"), 0));
		BigDecimal amountpromote_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amountpromote"), 0));
		BigDecimal amount_all = new BigDecimal(Types.parseInt(amounts.getFormatAttr("amount"), 0));
		order.setAmountsale(amountsale_all);
		order.setAmountpromote(amountpromote_all);
		order.setAmount(amount_all);
		sdao().update(order);
		
		Map remap = new DynamicObject();
		remap.put("state", "success");
		remap.put("id", orderid);
		
		return remap;
	}
	
	// 抢购订单结算
	public Map rushorder(DynamicObject form, DynamicObject login_token) throws Exception
	{

		
		Map remap = new DynamicObject();
		remap.put("state", "success");
		
		return remap;
	}

}
