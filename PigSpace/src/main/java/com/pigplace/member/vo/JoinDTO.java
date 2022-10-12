package com.pigplace.member.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <h1>ȸ������ ��û VO</h1>
 * @author heoseo
 *
 */
@Data
public class JoinDTO implements Serializable{

	private static final long serialVersionUID = -2549328862851803865L;
	
	private String userNm;
	
	private String userId;
	
	private String userPw;
	
	private String phoneNo;
	
	private String profileImg;
}