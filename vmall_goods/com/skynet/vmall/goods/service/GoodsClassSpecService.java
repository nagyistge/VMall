package com.skynet.vmall.goods.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetNameEntityService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.pojo.GoodsClassSpec;

@InjectName("goodsclassspecService")
@IocBean(args = { "refer:dao" }) 
public class GoodsClassSpecService extends SkynetNameEntityService<GoodsClassSpec>
{
	public GoodsClassSpecService()
	{
		super();
	}
	
	public GoodsClassSpecService(Dao dao)
	{
		super(dao);
	}	
	
	public GoodsClassSpecService(Dao dao, Class<GoodsClassSpec> entityType)
	{
		super(dao, entityType);
	}
	
	public List<DynamicObject> getGoodsClassSpeces(String goodclassid) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select classspec.* ");
		sql.append("   from t_app_goodsclassspec classspec, t_app_goodsclass supclass, t_app_goodsclass class ").append("\n");
		sql.append("  where 1 = 1").append("\n");
		sql.append("    and supclass.id = classspec.goodsclassid ").append("\n");
		// sql.append("    and supclass.internal like concat(class.internal, '%')").append("\n");
		sql.append("    and supclass.id = class.supid ").append("\n");
		sql.append("    and class.id = ").append(SQLParser.charValue(goodclassid)).append("\n");

		List<DynamicObject> specs = sdao().queryForList(sql.toString());	
		
		return specs;
	}
	
}
