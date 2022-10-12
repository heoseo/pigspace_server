package com.pigplace.member.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <h1>ȸ������ ��û VO</h1>
 * @author heoseo
 *
 */
@Data
public class LoginDTO implements Serializable{

	private static final long serialVersionUID = -6799977860974447667L;

	private String userId;
	
	private String userPw;
	
}