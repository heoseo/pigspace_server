package com.pigplace.common.util;

public class StringUtil {

	/**
	 * null Ȥ�� ������ �˻�
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if(str == null || str.equals("")) return true;
		else return false;
	}
}
