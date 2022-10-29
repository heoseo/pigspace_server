package com.pigspace.member.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <h1>로그인 요청 vo</h1>
 * @author heoseo
 *
 */
@Data
public class LoginDTO implements Serializable{

	private static final long serialVersionUID = -6799977860974447667L;

	private String userId;

	private String userPw;

}