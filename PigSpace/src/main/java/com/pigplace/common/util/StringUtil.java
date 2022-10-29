package com.pigplace.common.util;

public class StringUtil {

	/**
	 * null 이거나 빈값 판단
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.equals("")) return true;
		else return false;
	}
}
