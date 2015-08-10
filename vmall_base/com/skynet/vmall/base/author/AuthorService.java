package com.skynet.vmall.base.author;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.skynet.app.organ.service.GroupUserService;
import com.skynet.framework.common.encrypt.DES;
import com.skynet.framework.service.SkynetDaoService;
import com.skynet.framework.services.db.dybeans.DynamicObject;
import com.skynet.framework.services.function.HttpToolKit;
import com.skynet.framework.services.function.StringToolKit;

@InjectName("authorService")
@IocBean(args =
{ "refer:dao" })
public class AuthorService extends SkynetDaoService
{
	public AuthorService()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthorService(Dao dao)
	{
		super(dao);
		// TODO Auto-generated constructor stub
	}

	private final static String key = "vmall945@skynet.com!pjNB";

	public static String encode(String date) throws Exception
	{
		// System.out.println(date);
		byte date_a[] = date.getBytes();
		// System.out.println(date_a);
		byte date_b[] = DES.encode(date_a, key);
		// System.out.println(date_b);
		String date_c = DES.tostring(date_b);
		// System.out.println(date_c);
		return date_c;
	}

	public static String decode(String date_c) throws Exception
	{
		byte date_d[] = DES.tobytes(date_c);
		// System.out.println(date_d);
		byte date_e[] = DES.decode(date_d, key);
		// System.out.println(date_e);
		String date_f = new String(date_e);
		// System.out.println(date_f);
		return date_f;
	}

	public static String gentext(String ip, String wxopenid)
	{
		long timestamp = System.currentTimeMillis();
		String date = ip + "#" + wxopenid + "#" + timestamp;
		return date;
	}

	public static String getip(HttpServletRequest req)
	{
		String ip = "Unkown";
		try
		{
			ip = HttpToolKit.getIpAddr(req);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return ip;
	}

	public static String checksignature(String decode, String ip, String wxopenid)
	{
		String[] decodes = StringToolKit.split(decode, "#");
		if (decodes[0].equals(ip) && decodes[1].equals(wxopenid))
		{
			long current = System.currentTimeMillis();
			long passed = Long.valueOf(decodes[2]).longValue();
			if ((current - passed) > (1000 * 30))
			{
				return "timeout";
			}
			else
			{
				return "success";
			}
		}
		else
		{
			return "error";
		}
	}

	public static Map common_checksignature(String decode, String ip, String wxopenid)
	{
		Map remap = new DynamicObject();
		String state = checksignature(decode, ip, wxopenid);
		remap.put("state", state);
		if (state.equals("error"))
		{
			remap.put("message", "你的签名验证未通过，不能进行当前操作，请退出重试。");
			return remap;
		}
		if (state.equals("timeout"))
		{
			remap.put("message", "你的签名已过期，不能进行当前操作，请退出重试。");
			return remap;
		}

		return remap;
	}

	@Inject
	GroupUserService groupuserService;

	// 当前用户是否为指定的角色
	public boolean isarole(String loginname, String rolename)
	{
		boolean sign = false;

		long num = 0;

		num = groupuserService.count(Cnd.where("loginname", "=", loginname).and("groupname", "=", rolename).and("grouptype", "=", "ROLE"));

		if (num > 0)
		{
			sign = true;
			return sign;
		}

		return sign;
	}
}
