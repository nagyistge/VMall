package com.skynet.vmall.order.service;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;

import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.framework.services.function.Types;
import com.skynet.framework.spec.GlobalConstants;
import com.skynet.vmall.base.constants.VMallConstants;
import com.skynet.vmall.base.pojo.Goods;
import com.skynet.vmall.base.pojo.Member;
import com.skynet.vmall.base.pojo.Order;
import com.skynet.vmall.base.pojo.OrderGoods;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;
import com.skynet.vmall.goods.service.GoodsService;
import com.skynet.vmall.member.service.MemberService;

@InjectName("orderService")
@IocBean(args =
{ "refer:dao" })
public class OrderService extends SkynetNameEntityService<Order>
{
	
	@Inject
	GoodsService goodsService;
	
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

	// 浏览商品
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = (Integer) map.get("_page");
		int pagesize = (Integer) map.get("_pagesize");

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;
		
		String batchno = (String) map.get("batchno");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select id, cno, membercname, takeaddress, state, sum(amount) amount ").append("\n");
		sql.append(" from ( ").append("\n");
		sql.append(		
				" select vorder.id, vorder.cno, vorder.membercname, vorder.takeaddress, vorder.state, (ordergoods.nums * price.promoteprice) amount ")
				.append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods, t_app_goodsprice price ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and ordergoods.goodsid = price.goodsid ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid is not null ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid = price.eventitemgoodsid ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and vorder.state = '下单' ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(batchno))
		{
			sql.append("  and vorder.batchno = ").append(SQLParser.charValue(batchno)).append("\n");
		}
		sql.append(" union ").append("\n");
		sql.append(
				" select vorder.id, vorder.cno, vorder.membercname, vorder.takeaddress, vorder.state, (ordergoods.nums * price.promoteprice) amount ")
				.append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods, t_app_goodsprice price ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and ordergoods.eventitemgoodsid is null  ").append("\n");
		sql.append("     and ordergoods.goodsid = price.goodsid ").append("\n");
		sql.append("     and price.isdefault = '是' ").append("\n");
		sql.append("     and vorder.state = '下单' ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(batchno))
		{
			sql.append("  and vorder.batchno = ").append(SQLParser.charValue(batchno)).append("\n");
		}
		sql.append(" ) v ").append("\n");
		sql.append(" group by id, cno, membercname, takeaddress, state ").append("\n");
		
		sql.append(" union ").append("\n");
		sql.append(
				" select vorder.id, vorder.cno, vorder.membercname, vorder.takeaddress, vorder.state, sum(ordergoods.nums * ordergoods.realprice) amount ")				.append("\n");
		sql.append(" from t_app_order vorder, t_app_ordergoods ordergoods ").append("\n");
		sql.append(" where 1 = 1 ").append("\n");
		sql.append("     and vorder.id = ordergoods.orderid ").append("\n");
		sql.append("     and vorder.state <> '下单' ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(batchno))
		{
			sql.append("  and vorder.batchno = ").append(SQLParser.charValue(batchno)).append("\n");
		}
		sql.append("  group by vorder.id, vorder.cno, vorder.membercname, vorder.takeaddress, vorder.state ").append("\n");
		sql.append("   order by cno ").append("\n");


		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}

	// 转发
	public Map forward(Map form) throws Exception
	{
		String id = (String) form.get("id");

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
	
	// 付款
	public BigDecimal pay(String orderid, DynamicObject login_token) throws Exception
	{
		
		if(StringToolKit.isBlank(orderid))
		{
			throw new Exception("订单未找到，请核实订单。");
		}
		
		String userid = login_token.getFormatAttr(GlobalConstants.sys_login_userid);
		String userwxopenid = login_token.getFormatAttr(GlobalConstants.sys_login_userwxopenid);
		String username = login_token.getFormatAttr(GlobalConstants.sys_login_username);
		
		Order order = sdao().fetch(Order.class, orderid);
		
		List<OrderGoods> ordergoodses = sdao().query(OrderGoods.class, Cnd.where("orderid", "=", orderid));
		for(int i=0;i<ordergoodses.size();i++)
		{
			// 再次检查数量，防止非法数量；	
			int nums = ordergoodses.get(i).getNums();
			if(nums<=0)
			{
				continue;
			}
			
			OrderGoods ordergoods = ordergoodses.get(i);
			String goodsid = ordergoods.getGoodsid();
			Goods goods = sdao().fetch(Goods.class, goodsid);
			
			MemberService memberService = new MemberService(sdao(), Member.class);
			List<DynamicObject> supmembers = memberService.findsupmembers(userid, MemberService.level_rebate);

			// 如正常购买，单价、金额应为下订单时的商品实时价格
			// 如为参加活动购买，单价、金额应为活动的商品实时价格
			String eventitemgoodsid = ordergoods.getEventitemgoodsid();
			if(StringToolKit.isBlank(eventitemgoodsid))
			{
				ordergoods.setSaleprice(goods.getSaleprice());
				ordergoods.setPromoteprice(goods.getPromoteprice());
				ordergoods.setRealprice(goods.getPromoteprice());				
			}
			else
			{
				DynamicObject goodsprice = goodsService.getPrice(new DynamicObject(new String[]{"goodsid","eventitemgoodsid"}, new String[]{goodsid, eventitemgoodsid}));
				ordergoods.setSaleprice(new BigDecimal(goodsprice.getFormatAttr("saleprice"))); // 销售价（原价）
				ordergoods.setPromoteprice(new BigDecimal(goodsprice.getFormatAttr("promoteprice"))); // 促销价（现价）
				ordergoods.setRealprice(new BigDecimal(goodsprice.getFormatAttr("promoteprice")));					
			}
			
			BigDecimal amountsale = ordergoods.getSaleprice().multiply(new BigDecimal(ordergoods.getNums()));
			BigDecimal amountpromote = ordergoods.getPromoteprice().multiply(new BigDecimal(ordergoods.getNums()));
			BigDecimal amountreal = ordergoods.getRealprice().multiply(new BigDecimal(ordergoods.getNums()));				
			
			ordergoods.setAmountsale(amountsale);
			ordergoods.setAmountpromote(amountpromote);
			ordergoods.setAmountreal(amountreal);
			
			// 更新返利
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
			
			sdao().update(ordergoods); 

			sdao().clear(OrderGoodsRebate.class, Cnd.where("ordercno", "=", order.getCno()));
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
				orderrebate.setOrdergoodsid(ordergoods.getGoodsid());
				orderrebate.setOrdergoodsname(ordergoods.getGoodsname());
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
		
		return order.getAmount();
	}	

	// 付款处理
	public Map paynotify(Map form) throws Exception
	{
		String id = (String) form.get("id");
		String wxpayorderno = (String) form.get("wxpayorderno");
		String wxpaydeviceinfo = (String) form.get("wxpaydeviceinfo");
		String wxpaytransactionid = (String) form.get("wxpaytransactionid");
		String wxpaytradetype = (String) form.get("wxpaytradetype");
		String wxpaybanktype = (String) form.get("wxpaybanktype");
		String wxpaytotalfee = (String) form.get("wxpaytotalfee");
		String wxpayissubscribe = (String) form.get("wxpayissubscribe");
		String wxpaytimeend = (String) form.get("wxpaytimeend");
		String wxpayopenid = (String) form.get("wxpayopenid");

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
		String flownextstate = "收款";
		// 更新状态至下一环节
		sdao().update(Order.class, Chain.make("state", flownextstate), Cnd.where("id", "=", id));
		map.put("state", "success");
		map.put("flownextstate", flownextstate);
		
		StringBuffer s = new StringBuffer();
		s.append("{");
		s.append("paystate:'已支付',");
		s.append("wxpayorderno:'" + wxpayorderno + "',");
		s.append("wxpaydeviceinfo:'" + wxpaydeviceinfo + "',");
		s.append("thirdpaytradeno:'" + wxpaytransactionid + "',");
		s.append("wxpaytradetype:'" + wxpaytradetype + "',");
		s.append("wxpaybanktype:'" + wxpaybanktype + "',");
		s.append("wxpaytotalfee:'" + wxpaytotalfee + "',");
		
		s.append("wxpayissubscribe:'" + wxpayissubscribe + "',");
		s.append("wxpaytimeend:'" + wxpaytimeend + "',");
		s.append("wxpayopenid:'" + wxpayopenid + "',");
		s.append("paynotifytime:'" + (new Timestamp(System.currentTimeMillis())) + "'");
		s.append("}");
		
		System.out.println(s.toString());
		
		Chain c = Chain.from(Lang.map(s.toString()));
		// Chain chain = Chain.make("paystate", "已支付").make("paytime", new
		// Timestamp(System.currentTimeMillis())).make("thirdpaytradeno",
		// wxtransactionid);
		sdao().update(Order.class, c, Cnd.where("id", "=", id));
		return map;
	}

	public Map savetaker(Map map) throws Exception
	{
		Map remap = new HashMap();
		String id = (String) map.get("id");
		String takercname = (String) map.get("takercname");
		String takermobile = (String) map.get("takermobile");
		String takeprovince = (String) map.get("takeprovince");
		String takecity = (String) map.get("takecity");
		String takecounty = (String) map.get("takecounty");
		String taketown = (String) map.get("taketown");
		String takepostcode = (String) map.get("takepostcode");
		String takeaddress = (String) map.get("takeaddress");

		if (StringToolKit.isBlank(takercname))
		{
			remap.put("state", "error");
			remap.put("error", "请填写收货人姓名。");
			return remap;
		}

		if (StringToolKit.isBlank(takermobile))
		{
			remap.put("state", "error");
			remap.put("error", "请填写收货人联系电话。");
			return remap;
		}

		if (StringToolKit.isBlank(takeaddress))
		{
			remap.put("state", "error");
			remap.put("error", "请填写收货地址。");
			return remap;
		}

		Order order = sdao().fetch(Order.class, id);
		if (order == null)
		{
			remap.put("state", "error");
			remap.put("error", "订单信息异常，未找到当前订单。");
			return remap;
		}

		order.setTakercname(takercname);
		order.setTakermobile(takermobile);
		order.setTakeprovince(takeprovince);
		order.setTakecity(takecity);
		order.setTakecounty(takecounty);
		order.setTaketown(taketown);
		order.setTakepostcode(takepostcode);
		order.setTakeaddress(takeaddress);

		sdao().update(order);

		remap.put("state", "success");
		remap.put("order", locate(id));

		return remap;
	}

	public Map savepayer(Map map) throws Exception
	{
		Map remap = new HashMap();
		String id = (String) map.get("id");
		String membercname = (String) map.get("membercname");
		String phone = (String) map.get("phone");

		if (StringToolKit.isBlank(membercname))
		{
			remap.put("state", "error");
			remap.put("error", "未填写会员姓名，请检查个人资料后，重新下单。");
			return remap;
		}

		if (StringToolKit.isBlank(phone))
		{
			remap.put("state", "error");
			remap.put("error", "请填写购买人联系电话。");
			return remap;
		}

		Order order = sdao().fetch(Order.class, id);

		if (order == null)
		{
			remap.put("state", "error");
			remap.put("error", "订单信息异常，未找到当前订单。");
			return remap;
		}

		String memberid = order.getMemberid();
		String wxopenid = order.getWxopenid();

		if (StringToolKit.isBlank(memberid))
		{
			remap.put("state", "error");
			remap.put("error", "会员资料异常，请检查资料后，重新下单。");
			return remap;
		}

		if (StringToolKit.isBlank(wxopenid))
		{
			remap.put("state", "error");
			remap.put("error", "会员未注册微信账号，请检查资料后，重新下单。");
			return remap;
		}

		order.setMembercname(membercname);
		order.setPhone(phone);

		sdao().update(order);

		remap.put("state", "success");
		remap.put("order", locate(id));

		return remap;
	}

}
