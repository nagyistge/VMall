package com.skynet.framework.common.generator;

import java.util.Arrays;
import java.util.Random;

public class RandomGenerator
{
	public static int getValue(int length)
	{
		int max = 1;
		for(int i=0;i<length;i++)
		{
			max = max * 10;
		}
		
		int r = random(max);
		
		return r;
	}
	
	public static int random(int max)
	{
		Random ran = new Random();
		int r = 0;
		m1: while (true)
		{
			int n = ran.nextInt(max);
			r = n;
			int[] bs = new int[4];
			for (int i = 0; i < bs.length; i++)
			{
				bs[i] = n % 10;
				n /= 10;
			}
			Arrays.sort(bs);
			for (int i = 1; i < bs.length; i++)
			{
				if (bs[i - 1] == bs[i])
				{
					continue m1;
				}
			}
			break;
		}
		
		return r;
	}
}
