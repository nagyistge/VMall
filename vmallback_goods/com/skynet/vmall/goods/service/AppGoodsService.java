package com.skynet.vmall.goods.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.SQLParser;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.StringToolKit;
import com.skynet.vmall.base.query.QueryHelper;

@InjectName("appgoodsService")
@IocBean(args = { "refer:dao" }) 
public class AppGoodsService extends SkynetDaoService
{
	
	public AppGoodsService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppGoodsService(Dao dao)
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

		String classid = (String) map.get("classid");	
		String cname = (String) map.get("cname");
	

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from t_app_goods ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		// 增加查询过滤条件
		if (!StringToolKit.isBlank(classid))
		{
			sql.append("  and classid = ").append(SQLParser.charValue(classid)).append("\n");
		}
		
		if (!StringToolKit.isBlank(cname))
		{
			sql.append("  and cname like ").append(SQLParser.charLikeRightValue(cname)).append("\n");
		}		
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}
	
}
