package com.skynet.vmall.goods.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.Types;

@InjectName("appgoodsclassService")
@IocBean(args = { "refer:dao" }) 
public class AppGoodsClassService extends SkynetDaoService
{
	public AppGoodsClassService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppGoodsClassService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	// 浏览商品
	public List<DynamicObject> browse(Map map) throws Exception
	{
		int page = Types.parseInt((String) map.get("_page"), 1);
		int pagesize = Types.parseInt((String) map.get("_pagesize"), 10);

		int startindex = (page - 1) * pagesize;
		int endindex = page * pagesize;

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件

		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		return datas;
	}

}
