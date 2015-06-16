package com.skynet.vmall.base.author;

import javax.servlet.http.HttpServletRequest;

import com.skynet.framework.common.encrypt.DES;
import com.skynet.framework.services.function.HttpToolKit;
import com.skynet.framework.services.function.StringToolKit;

public class AuthorService
{
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
		catch(Exception e)
		{
			System.out.println(e);
		}
		return ip;
	}
	
	public static String checksignature(String decode, String ip, String wxopenid)
	{
		String[] decodes = StringToolKit.split(decode, "#");
		if(decodes[0].equals(ip) && decodes[1].equals(wxopenid))
		{
			long current = System.currentTimeMillis();
			long passed = Long.valueOf(decodes[2]).longValue();
			if((current - passed) > (1000 * 30))
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

}
