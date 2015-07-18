package com.skynet.vmall.goods.service;

import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;

@InjectName("appgoodsclassspecService")
@IocBean(args = { "refer:dao" }) 
public class AppGoodsClassSpecService extends SkynetDaoService
{	
	public AppGoodsClassSpecService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppGoodsClassSpecService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
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
