package com.skynet.vmall.test.main;

import com.skynet.framework.common.encrypt.DES;
import com.skynet.framework.common.encrypt.MD5;

public class TestSecurity
{

	public static void main(String[] args) throws Exception
	{
		System.out.println(MD5.GenMD5("kkxl7608"));
		//test();
	}
	
	public static void test() throws Exception
	{
		String key = "vmall945@skynet.com!pjNB";
		String ip = "192.168.1.168";
		String wxopenid = "ofcJis5AonAzEqhEciUAVOlo1XRY";
		long timestamp = System.currentTimeMillis();
		String date = ip + "#" + wxopenid + "#" + timestamp;
		System.out.println(date);
		byte date_a[] = date.getBytes();
		System.out.println(date_a);
		byte date_b[] = DES.encode(date_a, key);
		System.out.println(date_b);
		String date_c = DES.tostring(date_b);
		System.out.println(date_c);
		byte date_d[] = DES.tobytes(date_c);
		System.out.println(date_d);
		byte date_e[] = DES.decode(date_d, key);
		System.out.println(date_e);
		String date_f = new String(date_e);
		System.out.println(date_f);
		
		System.out.println(date_c.length());
	}
	
	public static void sec() throws Exception
	{
		long begin = System.currentTimeMillis();
		System.out.println(begin);
		String key = "vmall945";
		String ip = "192.168.1.168";
		String wxopenid = "ofcJis5AonAzEqhEciUAVOlo1XRY";
		long timestamp = System.currentTimeMillis();
		String text = ip + "#" + wxopenid + "#" + timestamp;
		byte[] encode = DES.encode(text.getBytes(), key);
		String encode_str = DES.tostring(encode);
		byte[] decode = DES.tobytes(encode_str);
		String decode_str = DES.tostring(DES.decode(decode, key));
		
		System.out.println(text);
//		System.out.println(encode);
//		System.out.println(decode);
		System.out.println(encode_str);
		System.out.println(decode_str);		
		
		long end = System.currentTimeMillis();
		System.out.println(end);
		System.out.println(end - begin);
	}

}
