package com.pigspace.common.support;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseEntity<T>{

	private int code;
	private String message;

	@Schema
	private T data;

	public ResponseEntity(int code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
