package com.pigspace.common.util;

import com.pigspace.common.support.LogObject;

public class HashUtil extends LogObject{

	private static final long serialVersionUID = -4010354876199810575L;

	/**
	 * <h1>회원 식별 번호</h1>
	 * @param userNm
	 * @param userId
	 * @param phoneNo
	 * @return
	 */
	public static Integer makeMbrHash(String userNm, String userId, String phoneNo) {

		return (  userNm + "|"
				+ userId + "|"
				+ phoneNo + "|" ).hashCode();

	}

}
