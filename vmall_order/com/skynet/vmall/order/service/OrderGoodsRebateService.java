package com.skynet.vmall.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;

@InjectName("ordergoodsrebateService")
@IocBean(args =
{ "refer:dao" })
public class OrderGoodsRebateService extends SkynetNameEntityService<OrderGoodsRebate>
{
	public OrderGoodsRebateService()
	{
		super();
	}

	public OrderGoodsRebateService(Dao dao)
	{
		super(dao);
	}

	public OrderGoodsRebateService(Dao dao, Class<OrderGoodsRebate> entityType)
	{
		super(dao, entityType);
	}
	
	public List<DynamicObject> memberscore(Map map) throws Exception
	{
		String memberid = (String)map.get("memberid");
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_ordergoodsrebate rebate ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		sql.append("  order by rebate.rebatetime desc ");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString());
		return datas;
	}
	
	public BigDecimal sumscore(Map map) throws Exception
	{
		String memberid = (String)map.get("memberid");
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(score) score from t_app_ordergoodsrebate rebate ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and rebate.supmemberid = ").append(SQLParser.charValue(memberid)).append("\n");
		
		int score = Types.parseInt(queryForMap(sql.toString()).getFormatAttr("score"), 0);
		
		BigDecimal sumscore = new BigDecimal(score).setScale(2);
		
		return sumscore;
	}

}
