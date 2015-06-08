package com.skynet.vmall.order.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.Order;

@InjectName("orderService")
@IocBean(args =
{ "refer:dao" })
public class OrderService extends SkynetNameEntityService<Order>
{
	public OrderService()
	{
		super();
	}

	public OrderService(Dao dao)
	{
		super(dao);
	}

	public OrderService(Dao dao, Class<Order> entityType)
	{
		super(dao, entityType);
	}

	public static String[] flow = new String[]
	{ "下单", "收款", "发货", "收货", "结束" };

	// 浏览商品
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 1);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}

	// 转发
	public Map forward(String id) throws Exception
	{
		Map map = new DynamicObject();
		DynamicObject order = locate(id);
		String flowstate = order.getFormatAttr("state");

		// 检查流程已结束异常
		if ("结束".equals(flowstate))
		{
			map.put("state", "error");
			map.put("errormessage", "订单流程已结束，不允许再转发处理！");
			return map;
		}

		// 检查未找到当前流程状态异常
		String flownextstate = flowstate;
		int index = StringToolKit.getTextInArrayIndex(OrderService.flow, flowstate);
		if (index == -1)
		{
			map.put("state", "error");
			map.put("errormessage", "未找到当前流程状态！");
			return map;
		}

		if (OrderService.flow.length > (index + 1))
		{
			flownextstate = OrderService.flow[index + 1];
		}

		// 更新状态至下一环节
		sdao().update(Order.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));
		map.put("state", "success");
		map.put("flownextstate", flownextstate);
		return map;
	}

}
