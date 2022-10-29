package com.pigplace.common.support;


import lombok.Data;

@Data
public class ResponseEntity<T>{

	private int code;
	private String message;
	private T data;
	
	public ResponseEntity(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
}
