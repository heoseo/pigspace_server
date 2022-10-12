package com.pigplace.common.util;

public class StringUtil {

	/**
	 * null 혹은 빈값인지 검사
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.equals("")) return true;
		else return false;
	}
}
