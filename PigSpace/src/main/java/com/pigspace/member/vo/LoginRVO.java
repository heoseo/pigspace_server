package com.pigspace.member.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <h1>로그인 응답 VO</h1>
 * @author heoseo
 *
 */
@Data
public class LoginRVO implements Serializable{

	private static final long serialVersionUID = -6799977860974447667L;


	/**
	 * 회원번호
	 */
	private String mbrNo;
	/**
	 * 인증여부
	 */
	private String verifiedYN;

	/**
	 * 닉네임설정여부
	 */
	private String nickSetYN;

}