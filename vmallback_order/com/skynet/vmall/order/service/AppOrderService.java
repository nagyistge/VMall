package com.skynet.vmall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.flow.pojo.RFlow;
import com.skynet.app.flow.service.AppFlowService;
import com.skynet.app.flow.service.BFlowService;
import com.skynet.app.flow.service.RFlowService;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.spec.GlobalConstants;
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
	AppFlowService appflowService;

	@Inject
	BFlowService bflowService;

	@Inject
	RFlowService rflowService;
	
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

		String state = (String) map.get("state");
		String mobile_orderno = (String) map.get("mobile_orderno");
		String goodscode = (String) map.get("goods_no");
		String takeaddress = (String) map.get("receiver_address");
		String ordertimebegin = (String) map.get("start_create_time");
		String ordertimeend = (String) map.get("end_create_time");

		StringBuffer sql = new StringBuffer();
		sql.append(" select vorder.* ");
		sql.append("  from t_app_order vorder ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");

		// 增加查询过滤条件
		if (!StringToolKit.isBlank(mobile_orderno))
		{
			sql.append("  and ( ");
			sql.append("  takercname like ").append(SQLParser.charLikeValue(mobile_orderno)).append("\n");
			sql.append("  or takermobile like ").append(SQLParser.charLikeValue(mobile_orderno)).append("\n");
			sql.append("  or cno like ").append(SQLParser.charLikeValue(mobile_orderno)).append("\n");
			sql.append("  ) ").append("\n");
		}

		if (!StringToolKit.isBlank(state))
		{
			sql.append("  and vorder.state = ").append(SQLParser.charValue(state)).append("\n");
		}

		if (!StringToolKit.isBlank(takeaddress))
		{
			sql.append("  and takeaddress like ").append(SQLParser.charLikeValue(takeaddress)).append("\n");
		}

		if (!StringToolKit.isBlank(ordertimebegin))
		{
			sql.append("  and datediff(ordertime, '" + ordertimebegin + "')>=0").append("\n");
		}
		
		if (!StringToolKit.isBlank(ordertimeend))
		{
			sql.append("  and datediff(ordertime, '" + ordertimeend + "')<=0").append("\n");
		}
		
		if (!StringToolKit.isBlank(goodscode))
		{
			sql.append("  and exists( ").append("\n");
			sql.append("  select id from t_app_ordergoods ").append("\n");
			sql.append("   where orderid = vorder.id ").append("\n");
			sql.append("     and ( ").append("\n");
			sql.append("         goodscode like ").append(SQLParser.charLikeValue(goodscode)).append("\n");
			sql.append("      or goodsname like ").append(SQLParser.charLikeValue(goodscode)).append("\n");
			sql.append("     )").append("\n");
			sql.append(" ) ").append("\n");
		}

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}

	// 转发
	public Map foward(DynamicObject form, DynamicObject login_token) throws Exception
	{
		String id = form.getAttr("id");
		DynamicObject order = orderService.locate(id);

		// 更新流程信息
		String dataid = order.getFormatAttr("id");
		String bflowid = bflowService.fetch(Cnd.where("cname", "=", VMallConstants.flow_order_name)).getId();
		RFlow rflow = rflowService.fetch(Cnd.where("dataid", "=", dataid).and("bflowid", "=", bflowid));
		String runflowkey = rflow.getRunflowkey();
		String sname = rflow.getState();

		DynamicObject swapflow = new DynamicObject();
		swapflow.setAttr("runflowkey", runflowkey);
		swapflow.setAttr("sname", sname);
		String flownextstate = sname;
		try
		{
			flownextstate = appflowService.forward(swapflow, login_token);

			// 更新状态至下一环节
			sdao().update(Order.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));
		}
		catch(Exception e)
		{
			DynamicObject ro = new DynamicObject();
			ro.setAttr("state", "error");
			ro.setAttr("message", e.getMessage());
			return ro;
		}
		
		DynamicObject ro = new DynamicObject();
		ro.setAttr("state", "success");
		ro.setAttr("flownextstate", flownextstate);
		return ro;
	}

	// 当前用户是否为指定的角色
	public boolean isarole(String loginname, String rolename)
	{
		boolean sign = false;

		long num = 0;
		// num = (Long)
		// userRoleDao.findUnique(" select count(*) from UserRole a where 1 = 1 and a.rname = ? and userid = ? ",
		// rolename, loginname);

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
	
	// 保存运费
	public Map savefreight(Map map, DynamicObject login_token) throws Exception
	{
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);

		String id = (String) map.get("id");

		Order order = orderService.fetch(id);
		// 订单下单阶段才可以修改运费
		if(!("下单".equals(order.getState())))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "订单在下单阶段才能修改运费！");
			return remap;			
		}
		
		// 订单付款前才可以修改运费
		if(!("未支付".equals(order.getPaystate())))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "订单在付款前才能修改运费！");
			return remap;			
		}
		
		// 订单付款前才可以修改运费
		if("结束".equals(order.getState()))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "订单已结束，不能再修改运费！");
			return remap;			
		}
		
		String nofreight = (String)map.get("nofreight");
		String freighttype = (String)map.get("freighttype");	
		BigDecimal freight = new BigDecimal((String)map.get("freight"));	
		
		order.setNofreight(nofreight);
		order.setFreighttype(freighttype);
		order.setFreight(freight);
		
		sdao().update(order);
		
		Map remap = new DynamicObject();
		remap.put("state", "success");
		remap.put("id", id);
		return remap;
		
	}
	
	// 保存物流
	public Map savelogistics(Map map, DynamicObject login_token) throws Exception
	{
		String id = (String) map.get("id");

		Order order = orderService.fetch(id);
		// 订单发货阶段才可以修改物流
		if(!"发货".equals(order.getState()))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "订单在发货阶段才能修改物流单据！");
			return remap;			
		}
		
		// 订单未结束才可以修改物流
		if("结束".equals(order.getState()))
		{
			Map remap = new DynamicObject();
			remap.put("state", "error");
			remap.put("message", "订单已结束，不能再修改物流单据！");
			return remap;			
		}
		
		String logisticscomp = (String)map.get("logisticscomp");
		String expressno = (String)map.get("expressno");	
		
		order.setLogisticscomp(logisticscomp);
		order.setExpressno(expressno);
		
		sdao().update(order);
		
		Map remap = new DynamicObject();
		remap.put("state", "success");
		remap.put("id", id);
		return remap;
		
	}

}
