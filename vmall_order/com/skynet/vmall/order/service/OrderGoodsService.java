package com.skynet.vmall.order.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.pojo.OrderGoods;

@InjectName("ordergoodsService")
@IocBean(args =
{ "refer:dao" })
public class OrderGoodsService extends SkynetNameEntityService<OrderGoods>
{
	public OrderGoodsService()
	{
		super();
	}

	public OrderGoodsService(Dao dao)
	{
		super(dao);
	}

	public OrderGoodsService(Dao dao, Class<OrderGoods> entityType)
	{
		super(dao, entityType);
	}
	
	// 浏览商品
	public List<DynamicObject> list(Map map) throws Exception
	{
		String orderid = (String)map.get("orderid");

		StringBuffer sql = new StringBuffer();

		// 后期增加 付款前按照商品实时价格查询，付款后按照订单商品价格查询
		sql.append(" select vorder.cno, ordergoods.id, ordergoods.goodsid, ordergoods.goodsname, ordergoods.eventitemgoodsid, ordergoods.nums, (ordergoods.nums * price.saleprice) amountsale, (ordergoods.nums * price.promoteprice) amountpromote, (ordergoods.nums * price.promoteprice) amountreal, price.saleprice, price.promoteprice, price.promoteprice realprice, goods.pic goodspic ").append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods, t_app_goodsprice price, t_app_goods goods ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and ordergoods.goodsid = price.goodsid ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid is not null ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid = price.eventitemgoodsid ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and ordergoods.goodsid = goods.id ").append("\n");
		sql.append("     and vorder.state = '下单' ").append("\n");		
		sql.append("     and vorder.id = ").append(SQLParser.charValue(orderid)).append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select vorder.cno, ordergoods.id, ordergoods.goodsid, ordergoods.goodsname, ordergoods.eventitemgoodsid, ordergoods.nums, (ordergoods.nums * price.saleprice) amountsale, (ordergoods.nums * price.promoteprice) amountpromote, (ordergoods.nums * price.promoteprice) amountreal, price.saleprice, price.promoteprice, price.promoteprice realprice, goods.pic goodspic ").append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods, t_app_goodsprice price, t_app_goods goods ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid is null  ").append("\n");
		sql.append("     and ordergoods.goodsid = price.goodsid ").append("\n");
		sql.append("     and price.isdefault = '是' ").append("\n");
		sql.append("     and ordergoods.goodsid = goods.id ").append("\n");	
		sql.append("     and vorder.state = '下单' ").append("\n");			
		sql.append("     and vorder.id = ").append(SQLParser.charValue(orderid)).append("\n");
		sql.append(" union ").append("\n");
		sql.append(" select vorder.cno, ordergoods.id, ordergoods.goodsid, ordergoods.goodsname, ordergoods.eventitemgoodsid, ordergoods.nums, (ordergoods.nums * ordergoods.saleprice) amountsale, (ordergoods.nums * ordergoods.promoteprice) amountpromote, (ordergoods.nums * ordergoods.realprice) amountreal, ordergoods.saleprice, ordergoods.promoteprice, ordergoods.realprice, goods.pic goodspic ").append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods, t_app_goodsprice price, t_app_goods goods ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and ordergoods.goodsid = goods.id ").append("\n");	
		sql.append("     and vorder.state <> '下单' ").append("\n");			
		sql.append("     and vorder.id = ").append(SQLParser.charValue(orderid)).append("\n");
		
		sql.append("   order by cno ").append("\n");
		
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString());

		return datas;
	}
	


}
