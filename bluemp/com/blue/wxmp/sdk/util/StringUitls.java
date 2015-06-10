package com.blue.wxmp.sdk.util;

import java.util.HashMap;

import org.nutz.json.Json;
import org.nutz.lang.Strings;

/**
 * 
 * @author gongrui bluegongrui@gmail.com
 *
 * @version create at 2015年6月4日 上午11:33:09
 */
public final class StringUitls {

	/**
	 * 此类不需要实例化
	 */
	private StringUitls() {
	}

	/**
	 * 判断一组字符串是否有空值
	 *
	 * @param strs
	 *            需要判断的一组字符串
	 * @return 判断结果，只要其中一个字符串为null或者为空，就返回true
	 */
	public static boolean hasBlank(String... strs) {
		if (null == strs || 0 == strs.length) {
			return true;
		} else {
			// 这种代码如果用java8就会很优雅了
			for (String str : strs) {
				if (Strings.isBlank(str)) {
					return true;
				}
			}
		}
		return false;
	}

	public static HashMap<String,String> parseFromUrl(String url) {
		if (url.indexOf("?") < 0)
			return null;
		
		HashMap<String, String> rmap = new HashMap<String, String>();
		rmap.put("original_url", url.split("\\?")[0]);
		
		String[] hashes = (url.split("\\?")[1]).split("\\&");
		
		for (int i = 0; i < hashes.length; i++) {
			String[] hash = hashes[i].split("=");
			rmap.put(hash[0], hash[1]);
		}
		return (rmap);
	}
	public static void main(String[] args) {
		
		System.out.println(Json.toJson(parseFromUrl("/index?a=1")));
	}
}
