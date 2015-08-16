package com.skynet.vmall.test.main;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.common.generator.RandomGenerator;
import com.skynet.framework.common.generator.SNGenerator;
import com.skynet.framework.common.generator.UUIDGenerator;

public class OrderInit
{
	public static void main(String[] args)
	{
		String cno = "000163507124";
		
		for (int i = 0; i < 3; i++)
		{
			String cno1 = cno + FormatKey.format(i + 1, 4);
			order(cno1);

			for (int j = 0; j < 3; j++)
			{
				String cno2 = cno1 + FormatKey.format(j + 1, 4);
				order(cno2);

				for (int k = 0; k < 3; k++)
				{
					String cno3 = cno2 + FormatKey.format(j + 1, 4);
					order(cno3);
				}
			}
		}
	}

	public static void order(String cno1)
	{
		String[] goodsids = new String[]
		{ "000100010003-0000-0000", "000100010003-0000-0001", "000100010003-0000-0100", "000100010003-0000-0101", "001100020007-0000-0000", "001100020007-0000-0002", "001100020007-0000-0200", "001100020007-0000-0100", "001100020007-0000-0101",
				"001100020007-0100-0200", "001100020007-0101-0002", "001100020007-0100-0002" };

		StringBuffer sql = new StringBuffer();

		int loop_order = RandomGenerator.getValue(1) % 3;
		for (int j = 0; j < loop_order; j++)
		{
			String orderid = UUIDGenerator.getInstance().getNextValue();
			String ordercno = SNGenerator.getValue(8);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			int day = RandomGenerator.getValue(2) % 28;
			int month = RandomGenerator.getValue(2) % 12;
			
			GregorianCalendar cal = new GregorianCalendar(2015, month, day);
			String ordertime = sf.format(cal.getTime());
			
			sql = new StringBuffer();
			sql.append("insert into t_app_order (id, cno, memberid, ordertime) ");
			sql.append("values ('" + orderid + "','" + ordercno + "','" + cno1 + "','" + ordertime + "'); ");

			System.out.println(sql.toString());

			int loop_ordergoods = RandomGenerator.getValue(1) % 4;
			for (int k = 0; k < loop_ordergoods; k++)
			{
				int loop_goods = RandomGenerator.getValue(1) % goodsids.length;
				String goodsid = goodsids[loop_goods];
				String ordergoodsid = UUIDGenerator.getInstance().getNextValue();
				int nums = RandomGenerator.getValue(1) % 3 + 1;

				sql = new StringBuffer();
				sql.append("insert into t_app_ordergoods (id, orderid, goodsid, nums) ");
				sql.append(" values ('" + ordergoodsid + "','" + orderid + "','" + goodsid + "'," + nums + "); ");

				System.out.println(sql.toString());
				
				for(int r=0;r<3;r++)
				{
					String rebateid = UUIDGenerator.getInstance().getNextValue();
					int len = cno1.length()-((r+1)*4);
					if(len<=0)
					{
						continue;
					}
					
					String supmemberid = cno1.substring(0, len);
				
					sql = new StringBuffer();
					sql.append("insert into t_app_ordergoodsrebate(id, ordercno, ordergoodsid, supmemberid, submemberid, level, nums, score) ");
					sql.append("values ('"+rebateid+"','"+ordercno+"','"+ordergoodsid+"','"+supmemberid+"','"+cno1+"',"+(r+1)+","+nums+", 5); ");
				
					System.out.println(sql.toString());
				}	
			}
		}
	}
}
