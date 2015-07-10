package com.blue.wxmp.sdk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.blue.wxmp.sdk.api.ApiConfigKit;

public class SignUtils {

	public static final SignUtils me = new SignUtils();

	private static final Log log = Logs.getLog(SignUtils.class);
	private static final char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public boolean checkSignature(String signature, String timestamp, String nonce) {
		String token = ApiConfigKit.apiConfig.getToken();

		if (StringUitls.hasBlank(token, signature, timestamp, nonce)) {
			return false;
		}
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (String anArr : arr) {
			content.append(anArr);
		}
		MessageDigest md;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(content.toString().getBytes("UTF-8"));
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			log.error("加密方式异常", e);
		} catch (UnsupportedEncodingException e) {
			log.error("编码格式不支持", e);
		}
		return tmpStr != null && tmpStr.equalsIgnoreCase(signature);

	}

	public boolean checkSignature(HttpServletRequest req) {

		return checkSignature(req.getAttribute("signature").toString(), req.getAttribute("timestamp").toString(), req.getAttribute("nonce").toString());
	}

	/**
	 * 将byte数组变为16进制对应的字符串
	 *
	 * @param byteArray
	 *            byte数组
	 * @return 转换结果
	 */
	private static String byteToStr(byte[] byteArray) {
		int len = byteArray.length;
		StringBuilder strDigest = new StringBuilder(len * 2);
		for (byte aByteArray : byteArray) {
			strDigest.append(byteToHexStr(aByteArray));
		}
		return strDigest.toString();
	}

	private static String byteToHexStr(byte mByte) {
		char[] tempArr = new char[2];
		tempArr[0] = digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = digit[mByte & 0X0F];
		return new String(tempArr);
	}

	
}
