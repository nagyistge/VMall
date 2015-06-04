package com.skynet.vmall.order.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.pojo.OrderGoods;
import com.skynet.vmall.base.pojo.ShopCart;
import com.skynet.vmall.base.pojo.ShopCartGoods;

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
		sql.append(" select cartgoods.* from t_app_shopcartgoods cartgoods, t_app_shopcart cart ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and cart.memberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("    and cart.id = cartgoods.shopcartid ").append("\n");
		
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
		cartgoods.setId(UUIDGenerator.getInstance().getNextValue());
		cartgoods.setShopcartid(cart.getId());
		cartgoods.setMemberid("");
		cartgoods.setWxopenid("");		
		cartgoods.setGoodsid(goodsid);
		cartgoods.setGoodsname(goods.getCname());
		cartgoods.setNums(nums);

		sdao().insert(cartgoods);
				
		Map map = new DynamicObject();
		map.put("state", "success");
		map.put("shopcart", cart);
		return map;
	}
	
	// 购物车结算，生成正式订单。
	public Map settlement(DynamicObject form, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		
		List<String> ids = (List<String>)form.get("ids");
		for(int i=0;i<ids.size();i++)
		{
			// 生成订单
			String id = ids.get(i);
			ShopCartGoods cartgoods = sdao().fetch(ShopCartGoods.class, id);
			Goods goods = sdao().fetch(Goods.class, cartgoods.getGoodsid());
			String goodsclassid = goods.getClassid();
			
			Order order = new Order();
			String orderid = UUIDGenerator.getInstance().getNextValue();
			order.setId(orderid);
			order.setMemberid(userid);
			order.setWxopenid(userwxopenid);
			order.setOrdertime(new Timestamp(System.currentTimeMillis()));
			sdao().insert(order);
			
			// 生成订单商品记录
			OrderGoods ordergoods = new OrderGoods();
			String ordergoodsid = UUIDGenerator.getInstance().getNextValue();
			ordergoods.setId(ordergoodsid);
			ordergoods.setOrderid(orderid);
			ordergoods.setMemberid("");
			ordergoods.setWxopenid("");
			ordergoods.setGoodsid(cartgoods.getGoodsid());
			ordergoods.setGoodsname(cartgoods.getGoodsname());
			ordergoods.setNums(cartgoods.getNums());
			ordergoods.setSaleprice(goods.getSaleprice());
			
			sdao().insert(ordergoods); 
			
			// 清除购物车商品
			sdao().delete(cartgoods);
		}
		
		Map map = new DynamicObject();
		map.put("state", "success");
		
		return map;
	}
	

}