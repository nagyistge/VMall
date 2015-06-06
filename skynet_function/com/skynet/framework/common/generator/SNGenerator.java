package com.skynet.framework.common.generator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SNGenerator
{
	public static String getValue(int length)
	{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String sysdate = sf.format(new Date());
		String sn = sysdate + "-" + FormatKey.format(RandomGenerator.getValue(length), length);
		return sn;
	}
}
