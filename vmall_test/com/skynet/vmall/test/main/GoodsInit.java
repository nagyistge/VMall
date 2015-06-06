package com.skynet.vmall.test.main;

import com.skynet.framework.common.generator.FormatKey;

public class GoodsInit
{

	public static void main(String[] args)
	{
		for(int i=0;i<10;i++)
		{
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into t_app_goods(id, cname, classid, costprice, saleprice, promoteprice, rebate1, rebate2, rebate3) ");
			sql.append(" values(uuid(), '镂空时装水钻凉靴"+FormatKey.format(i+1, 2)+"','fushi',"+(60+(i*5+i))+","+(60+(i*5+i))+","+(40+(i*3+i))+",5,10,15);");
			System.out.println(sql.toString());
		}

	}

}
