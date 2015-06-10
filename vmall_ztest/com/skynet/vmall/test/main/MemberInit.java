package com.skynet.vmall.test.main;

import com.skynet.framework.common.generator.FormatKey;

public class MemberInit
{

	public static void main(String[] args)
	{
		StringBuffer sql = new StringBuffer();
		for (int i = 0; i < 5; i++)
		{
			String cno1 = FormatKey.format(i + 1, 4);
			sql = new StringBuffer();
			sql.append(" insert into t_app_member(id, wxopenid, cname, supid, internal, level)  ");
			sql.append(" values('" + cno1 + "','wx" + cno1 + "','用户" + cno1 + "', 'wph', '" + cno1 + "', 3); ");
			System.out.println(sql.toString());

			for (int j = 0; j < 5; j++)
			{
				String cno2 = cno1 + FormatKey.format(j + 1, 4);

				sql = new StringBuffer();
				sql.append(" insert into t_app_member(id, wxopenid, cname, supid, level)  ");
				sql.append(" values('" + cno2 + "','wx" + cno2 + "','用户" + cno2 + "', '" + cno1 + "', '" + cno2 + "', 4); ");
				System.out.println(sql.toString());

				for (int k = 0; k < 5; k++)
				{
					String cno3 = cno2 + FormatKey.format(k + 1, 4);

					sql = new StringBuffer();
					sql.append(" insert into t_app_member(id, wxopenid, cname, supid, level)  ");
					sql.append(" values('" + cno3 + "','wx" + cno3 + "','用户" + cno3 + "', '" + cno2 + "', '" + cno3 + "', 5); ");
					System.out.println(sql.toString());
					
					for (int m = 0; m < 5; m++)
					{
						String cno4 = cno3 + FormatKey.format(m + 1, 4);

						sql = new StringBuffer();
						sql.append(" insert into t_app_member(id, wxopenid, cname, supid, level)  ");
						sql.append(" values('" + cno4 + "','wx" + cno4 + "','用户" + cno4 + "', '" + cno3 + "', '" + cno4 + "', 6); ");
						System.out.println(sql.toString());
					}

				}

			}
		}

	}

}
