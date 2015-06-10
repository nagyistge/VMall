package com.skynet.vmall.test.main;

import com.skynet.framework.common.generator.UUIDGenerator;

public class GoodsInit
{

	public static void main(String[] args)
	{
		init_goodsclassspec("00010002", new String[]
		{ "尺码", "颜色" });
		init_goods("000100020007", new String[]
		{ "法兰米高2015夏季新款中坡跟蝴蝶结露趾女凉鞋", "欧百特凉拖鞋女夏平跟欧美新款2015羊皮软面人字拖夹趾百搭平底真皮凉拖C029", "少女猫2015夏季新款舒适铆钉平底女凉鞋拼色小坡跟凉拖鞋2522", "少女猫2015夏季新款舒适T型绑带女凉鞋金属饰坡跟女鞋2529", "奥康女鞋 新款韩版真皮舒适休闲低帮鞋 平底平跟女单鞋133223051" });
	}

	public static void init_goodsclassspec(String goodclassid, String[] specclasses)
	{
		StringBuffer sql = new StringBuffer();

		for (int i = 0; i < specclasses.length; i++)
		{
			String goodsclassspecid = UUIDGenerator.getInstance().getNextValue();
			sql = new StringBuffer();
			sql.append(" insert into t_app_goodsclassspec(id, goodsclassid, sno, specclass)");
			sql.append(" values('" + goodsclassspecid + "','" + goodclassid + "'," + (i + 1) + ",'" + specclasses[i] + "');");
			System.out.println(sql.toString());
		}
	}

	public static void init_goods(String goodsclassid, String[] goodses)
	{
		StringBuffer sql = new StringBuffer();

		String[] sp_color = new String[]
		{ "白色", "红色", "绿色" };
		String[] sp_size = new String[]
		{ "34", "36", "38" };

		for (int i = 0; i < goodses.length; i++)
		{
			String supgoodsid = UUIDGenerator.getInstance().getNextValue();
			sql = new StringBuffer();
			sql.append(" insert into t_app_goods(id, cname, classid, ctype) ");
			sql.append(" values('" + supgoodsid + "','" + goodses[i] + "','" + goodsclassid + "','商品');");
			System.out.println(sql.toString());

			for (int j = 0; j < sp_color.length; j++)
			{
				String supgoodsspecid = UUIDGenerator.getInstance().getNextValue();
				sql = new StringBuffer();
				sql.append(" insert into t_app_goodsspec(id, goodsid, specclass, spec)");
				sql.append(" values('" + supgoodsspecid + "','" + supgoodsid + "', '颜色','" + sp_color[j] + "');");
				System.out.println(sql.toString());

			}

			for (int k = 0; k < sp_size.length; k++)
			{
				String supgoodsspecid = UUIDGenerator.getInstance().getNextValue();
				sql = new StringBuffer();
				sql.append(" insert into t_app_goodsspec(id, goodsid, specclass, spec)");
				sql.append(" values('" + supgoodsspecid + "','" + supgoodsid + "', '尺码','" + sp_size[k] + "');");
				System.out.println(sql.toString());
			}

			for (int j = 0; j < sp_color.length; j++)
			{
				for (int k = 0; k < sp_size.length; k++)
				{
					String goodsid = UUIDGenerator.getInstance().getNextValue();
					int random = (((i + 4) % 3) * 5 + 10) + (((j * k) % 6) * 2) + k;
					sql = new StringBuffer();
					sql.append(" insert into t_app_goods(id, supid, cname, classid, ctype, costprice, saleprice, promoteprice, rebate1, rebate2, rebate3) ");
					sql.append(" values('" + goodsid + "','" + supgoodsid + "','" + goodses[i] + "【" + sp_color[j] + "】【" + sp_size[k] + "】','" + goodsclassid + "','货品'," + (random + (i)) + "," + (random + (i * 2 + j)) + "," + (random + (i + 2))
							+ ",6,12,18);");
					System.out.println(sql.toString());

					String goodsspecid = UUIDGenerator.getInstance().getNextValue();
					sql = new StringBuffer();
					sql.append(" insert into t_app_goodsspec(id, goodsid, specclass, spec)");
					sql.append(" values('" + goodsspecid + "','" + goodsid + "', '尺码','" + sp_size[k] + "');");
					System.out.println(sql.toString());

					goodsspecid = UUIDGenerator.getInstance().getNextValue();
					sql = new StringBuffer();
					sql.append(" insert into t_app_goodsspec(id, goodsid, specclass, spec)");
					sql.append(" values('" + goodsspecid + "','" + goodsid + "', '颜色','" + sp_color[j] + "');");
					System.out.println(sql.toString());
				}
			}
		}

	}

}
