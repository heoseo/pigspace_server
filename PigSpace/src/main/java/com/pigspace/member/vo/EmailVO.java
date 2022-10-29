package com.pigspace.member.vo;

import lombok.Data;

@Data
public class EmailVO {

	private String receiverEmail;
	private String subject;
	private String text;
}
