package com.skynet.vmall.order.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
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
		sql.append(" select ordergoods.*, goods.pic goodspic").append("\n");
		sql.append("   from t_app_ordergoods ordergoods, t_app_goods goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and ordergoods.orderid = ").append(SQLParser.charValue(orderid)).append("\n");
		sql.append("    and ordergoods.goodsid = goods.id ").append("\n");
		
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString());

		return datas;
	}

}
