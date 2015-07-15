package com.skynet.vmall.order.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.service.OrderGoodsService;
import com.skynet.vmall.base.service.OrderService;

@InjectName("appordergoodsService")
@IocBean(args =
{ "refer:dao" })
public class AppOrderGoodsService extends SkynetDaoService
{
	@Inject
	OrderService orderService;

	@Inject
	OrderGoodsService ordergoodsService;
	
	public AppOrderGoodsService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppOrderGoodsService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	public List<DynamicObject> list(Map map) throws Exception
	{
		String orderid = (String) map.get("orderid");

		StringBuffer sql = new StringBuffer();
		sql.append(" select ordergoods.* ");
		sql.append("  from t_app_ordergoods ordergoods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");

		if (!StringToolKit.isBlank(orderid))
		{
			sql.append("  and ordergoods.orderid = ").append(SQLParser.charValue(orderid)).append("\n");
		}

		List<DynamicObject> datas = sdao().queryForList(sql.toString());

		return datas;
	}
}
