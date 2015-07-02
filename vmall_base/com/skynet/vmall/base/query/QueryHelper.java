package com.skynet.vmall.base.query;

import java.util.Map;

import org.nutz.dao.Dao;

import com.skynet.framework.dao.SkynetDao;
import com.skynet.framework.services.function.Types;
import com.skynet.vmall.base.constants.VMallConstants;

public class QueryHelper
{
	Dao dao;

	public void page(String sql, int page, int pagesize, Map map) throws Exception
	{
		int nums = Types.parseInt(sdao().queryForMap(countsql(sql.toString())).getFormatAttr("nums"), 0);
		int maxpage = maxpage(nums, pagesize);
		int startpage = startpage(page, VMallConstants.pagenavnums);
		int endpage = endpage(page, VMallConstants.pagenavnums);
		map.put("_maxpage", maxpage);
		map.put("_startpage", startpage);
		map.put("_endpage", endpage);
	}

	public static String countsql(String s)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(0) nums ").append("\n");
		sql.append("   from ( ").append("\n");
		sql.append(s).append("\n");
		sql.append(" ) v ").append("\n");
		return sql.toString();
	}

	public static int maxpage(int nums, int pagesize)
	{
		int maxpage = 0;
		if (nums % pagesize == 0)
		{
			maxpage = nums / pagesize;
		}
		else
		{
			maxpage = nums / pagesize + 1;
		}
		System.out.println("maxpage:" + maxpage);
		return maxpage;
	}

	public static int startpage(int nums, int pagesize)
	{
		int startpage = 0;

		if (nums == 0)
		{
			startpage = 1;
			return startpage;
		}

		if (nums % pagesize == 0)
		{
			startpage = ((nums / pagesize) - 1) * pagesize + 1;
		}
		else
		{
			startpage = (nums / pagesize) * pagesize + 1;
		}

		System.out.println("startpage:" + startpage);

		return startpage;
	}

	public static int endpage(int nums, int pagesize)
	{
		int endpage = 0;

		if (nums == 0)
		{
			endpage = pagesize;
			return endpage;
		}

		if (nums % pagesize == 0)
		{
			endpage = (nums / pagesize) * pagesize;
		}
		else
		{
			endpage = (nums / pagesize) * pagesize + pagesize;
		}

		System.out.println("endpage:" + endpage);

		return endpage;
	}

	public static void main(String[] args) throws Exception
	{
		int cpage = 0;
		startpage(cpage, 10);
		endpage(cpage, 10);
	}
	
	public SkynetDao sdao()
	{
		return (SkynetDao) dao();
	}
	
	public Dao getDao()
	{
		return dao;
	}

	public void setDao(Dao dao)
	{
		this.dao = dao;
	}

	public Dao dao()
	{
		return this.dao;
	}

}
