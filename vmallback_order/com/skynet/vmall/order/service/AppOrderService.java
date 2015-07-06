package com.skynet.vmall.order.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.query.QueryHelper;
import com.skynet.vmall.base.service.OrderService;

@InjectName("apporderService")
@IocBean(args =
{ "refer:dao" })
public class AppOrderService extends SkynetDaoService
{
	@Inject
	OrderService orderService;
	
	public AppOrderService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppOrderService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = (Integer) map.get("_page");
		int pagesize = (Integer) map.get("_pagesize");

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		String cno = (String) map.get("cno");
		String state = (String) map.get("state");

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_order ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(cno))
		{
			sql.append("  and cno like ").append(SQLParser.charLikeRightValue(cno)).append("\n");
		}
		
		if (!StringToolKit.isBlank(state))
		{
			sql.append("  and state = ").append(SQLParser.charValue(state)).append("\n");
		}		

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}
	
	// 转发
	public Map foward(String id, String loginname) throws Exception
	{
		DynamicObject order = orderService.locate(id);
		String flowstate = order.getFormatAttr("state");

		Map map = new DynamicObject();

		if (!isarole(loginname, "订单管理用户"))
		{
			map.put("state", "error");
			map.put("errormessage", "非管理人员不能转发订单！");
			return map;
		}

		// 检查流程已结束异常
		if ("结束".equals(flowstate))
		{
			map.put("state", "error");
			map.put("errormessage", "流程已结束！");
			return map;
		}

		// 检查未找到当前流程状态异常
		String flownextstate = flowstate;
		int index = StringToolKit.getTextInArrayIndex(VMallConstants.flow_order, flowstate);
		if (index == -1)
		{
			map.put("state", "error");
			map.put("errormessage", "未找到当前流程状态！");
			return map;
		}

		if (VMallConstants.flow_order.length > (index + 1))
		{
			flownextstate = VMallConstants.flow_order[index + 1];
		}

		// 更新状态至下一环节
		sdao().update(Order.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));

		map.put("state", "success");
		map.put("flownextstate", flownextstate);
		return map;
	}
	
	// 当前用户是否为指定的角色
	public boolean isarole(String loginname, String rolename)
	{
		boolean sign = false;

		long num = 0;
//		num = (Long) userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ", rolename, loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
}
