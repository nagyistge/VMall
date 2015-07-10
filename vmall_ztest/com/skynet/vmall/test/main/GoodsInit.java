package com.skynet.vmall.test.main;

import com.skynet.framework.common.generator.FormatKey;
import com.skynet.framework.common.generator.UUIDGenerator;

public class GoodsInit
{

	public static void main(String[] args)
	{
		//init_goodsclassspec("00110002", new String[]{ "尺码", "颜色" });
		
		
		String[] goodses = new String[]
				{ "法兰米高2015夏季新款中坡跟蝴蝶结露趾女凉鞋", "欧百特凉拖鞋女夏平跟欧美新款2015羊皮软面人字拖夹趾百搭平底真皮凉拖"};
		String[] sp_color = new String[]{ "白色", "红色", "绿色" };
		String[] sp_size = new String[]	{ "34", "36", "38" };
		//init_goods("001100020007", goodses, sp_color, sp_size, 0);
		
		goodses = new String[]{"少女猫2015夏季新款舒适铆钉平底女凉鞋拼色小坡跟凉拖鞋", "奥康女鞋 新款韩版真皮舒适休闲低帮鞋 平底平跟女单鞋"};
		sp_color = new String[]{ "红色", "黑色", "粉色" };
		sp_size = new String[]	{ "33", "35", "37" };	
		//init_goods("001100020007", goodses, sp_color, sp_size, 100);
		
		init_goodsclassspec("00010001", new String[]
		{ "尺码", "颜色" });
		goodses = new String[]{"2015新品女士夏装荷叶边雪纺衫白色纯棉短袖女t恤上衣修身原宿风", "夏新品女装韩版学生眼镜米奇短袖女中长款宽松t恤休闲长T连衣裙潮"};
		sp_color = new String[]{ "红色", "黑色" };
		sp_size = new String[]	{ "S", "M", "L", "XL"};			
		init_goods("000100010003", goodses, sp_color, sp_size, 0);		
		
		
	}

	public static void init_goodsclassspec(String goodclassid, String[] specclasses)
	{
		StringBuffer sql = new StringBuffer();

		for (int i = 0; i < specclasses.length; i++)
		{
			String goodsclassspecid = UUIDGenerator.getInstance().getNextValue();
			sql = new StringBuffer();
			sql.append("insert into t_app_goodsclassspec(id, goodsclassid, sno, specclass)");
			sql.append(" values('" + goodsclassspecid + "','" + goodclassid + "'," + (i + 1) + ",'" + specclasses[i] + "');");
			System.out.println(sql.toString());
		}
	}

	public static void init_goods(String goodsclassid, String[] goodses, String[] sp_color, String[] sp_size, int index)
	{
		StringBuffer sql = new StringBuffer();

		for (int i = 0; i < goodses.length; i++)
		{
			// String supgoodsid = UUIDGenerator.getInstance().getNextValue();
			String supgoodsid = goodsclassid + "-" + FormatKey.format(i+index, 4);
			sql = new StringBuffer();
			sql.append("insert into t_app_goods(id, cname, classid, ctype) ");
			sql.append(" values('" + supgoodsid + "','" + goodses[i] + "','" + goodsclassid + "','商品');");
			System.out.println(sql.toString());

			for (int j = 0; j < sp_color.length; j++)
			{
				String supgoodsspecid = UUIDGenerator.getInstance().getNextValue();
				sql = new StringBuffer();
				sql.append("insert into t_app_goodsspec(id, goodsid, specclass, spec)");
				sql.append(" values('" + supgoodsspecid + "','" + supgoodsid + "', '颜色','" + sp_color[j] + "');");
				System.out.println(sql.toString());

			}

			for (int k = 0; k < sp_size.length; k++)
			{
				String supgoodsspecid = UUIDGenerator.getInstance().getNextValue();
				sql = new StringBuffer();
				sql.append("insert into t_app_goodsspec(id, goodsid, specclass, spec)");
				sql.append(" values('" + supgoodsspecid + "','" + supgoodsid + "', '尺码','" + sp_size[k] + "');");
				System.out.println(sql.toString());
			}

			for (int j = 0; j < sp_color.length; j++)
			{
				for (int k = 0; k < sp_size.length; k++)
				{
					// String goodsid = UUIDGenerator.getInstance().getNextValue();
					
					String goodsid = supgoodsid + "-" + FormatKey.format(j, 2) + FormatKey.format(k, 2);
					int random = (((i + 4) % 3) * 5 + 10) + (((j * k) % 6) * 2) + k;
					sql = new StringBuffer();
					sql.append("insert into t_app_goods(id, supid, cname, classid, ctype, costprice, saleprice, promoteprice, rebate1, rebate2, rebate3) ");
					sql.append(" values('" + goodsid + "','" + supgoodsid + "','" + goodses[i] + "【" + sp_color[j] + "】【" + sp_size[k] + "】','" + goodsclassid + "','货品'," + (random + (i)) + "," + (random + (i * 2 + j)) + "," + (random + (i + 2))
							+ ",6,12,18);");
					System.out.println(sql.toString());

					String goodsspecid = UUIDGenerator.getInstance().getNextValue();
					sql = new StringBuffer();
					sql.append("insert into t_app_goodsspec(id, goodsid, specclass, spec)");
					sql.append(" values('" + goodsspecid + "','" + goodsid + "', '尺码','" + sp_size[k] + "');");
					System.out.println(sql.toString());

					goodsspecid = UUIDGenerator.getInstance().getNextValue();
					sql = new StringBuffer();
					sql.append("insert into t_app_goodsspec(id, goodsid, specclass, spec)");
					sql.append(" values('" + goodsspecid + "','" + goodsid + "', '颜色','" + sp_color[j] + "');");
					System.out.println(sql.toString());
				}
			}
		}

	}

}
