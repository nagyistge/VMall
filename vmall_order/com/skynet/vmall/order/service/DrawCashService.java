package com.skynet.vmall.order.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.common.generator.SNGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;
import com.skynet.framework.service.GeneratorService;
import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.pojo.DrawCash;
import com.skynet.vmall.base.pojo.OrderGoodsRebate;

@InjectName("drawcashService")
@IocBean(args = { "refer:dao" }) 
public class DrawCashService extends SkynetNameEntityService<DrawCash>
{
	public final static String state_apply = "申请";
	
	@Inject
	GeneratorService generatorService;
	
	public DrawCashService()
	{
		super();
	}
	
	public DrawCashService(Dao dao)
	{
		super(dao);
	}	
	
	public DrawCashService(Dao dao, Class<DrawCash> entityType)
	{
		super(dao, entityType);
	}
	
	// 浏览提现
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 1);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_drawcash ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}
	
	public Map insert(DrawCash drawcash, DynamicObject login_token) throws Exception
	{
		Map remap = new DynamicObject();
		
		String id = UUIDGenerator.getInstance().getNextValue();
		String cno = SNGenerator.getValue(8);
	
		// 检查是否有未提现的订单商品，如果没有可提现的订单商品，不生成提现申请单。
		int nums = sdao().count(OrderGoodsRebate.class, Cnd.where("supmemberid", "=", drawcash.getMemberid()).and("supwxopenid", "=", drawcash.getMemberwxopenid()).and("drawcashid", "is", null).and("drawcashcno", "is", null).and("state", "is", null));
		if(nums==0)
		{
			remap.put("state", "error");
			remap.put("message", "没有可提现积分，不能申请提现！");
			return remap;
		}
		
		drawcash.setId(id);
		drawcash.setCno(cno);
		drawcash.setApplytime(new Timestamp(System.currentTimeMillis()));
		drawcash.setState(DrawCashService.state_apply);
		
		sdao().insert(drawcash);
		
		// 更新提现订单详细信息
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update t_app_ordergoodsrebate ");
		sql.append("    set state = ").append(SQLParser.charValueEnd(DrawCashService.state_apply)).append("\n");
		sql.append("  drawcashid = ").append(SQLParser.charValueEnd(drawcash.getId())).append("\n");
		sql.append("  drawcashcno = ").append(SQLParser.charValue(drawcash.getCno())).append("\n");
		// sql.append("  drawapplytime = ").append(SQLParser.charValue(drawcash.getApplytime().toString())).append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		sql.append("    and supmemberid = ").append(SQLParser.charValue(drawcash.getMemberid())).append("\n");
		sql.append("    and supwxopenid = ").append(SQLParser.charValue(drawcash.getMemberwxopenid())).append("\n");
		sql.append("    and drawcashid is null ").append("\n");
		sql.append("    and drawcashcno is null ").append("\n");
		sql.append("    and state is null ").append("\n");
		
		sdao().execute(Sqls.create(sql.toString()));
		
		remap.put("state", "success");
		remap.put("drawcash", drawcash);
		
		return remap;
	}
	
}
