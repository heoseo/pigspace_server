package com.pigspace.common.util;

import com.pigspace.common.support.LogObject;

public class HashUtil extends LogObject{

	private static final long serialVersionUID = -4010354876199810575L;

	/**
	 * <h1>해시코드 생성</h1>
	 * @return
	 */
	public static Integer makeHash(String... info) {

		String str = "";

		for(String i : info) {
			str = str.concat(i + "|");
		}

		return str.substring(0, str.length()-1).hashCode();

	}

}
