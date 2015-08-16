package com.skynet.vmall.goods.service;

import java.util.List;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.vmall.base.query.QueryHelper;

@InjectName("appdealerService")
@IocBean(args =
{ "refer:dao" })
public class AppDealerService extends SkynetDaoService
{
	public AppDealerService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AppDealerService(Dao dao)
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

		StringBuffer sql = new StringBuffer();
		sql.append(" select * ").append("\n");
		sql.append("   from t_app_dealer dealer ").append("\n");
		sql.append("  where 1 = 1 ").append("\n");
		
		List<DynamicObject> datas = sdao().queryForList(sql.toString(), startindex, endindex);

		QueryHelper helper = new QueryHelper();
		helper.setDao(sdao());
		helper.page(sql.toString(), page, pagesize, map);

		return datas;
	}

}
