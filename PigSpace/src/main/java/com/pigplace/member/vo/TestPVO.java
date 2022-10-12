package com.pigplace.member.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class TestPVO implements Serializable{
	
	private static final long serialVersionUID = 3717285330391413068L;
	
	private String id;
    private String pw;
}