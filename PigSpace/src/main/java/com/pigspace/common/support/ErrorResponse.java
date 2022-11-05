package com.pigspace.common.support;

import lombok.Data;

@Data
public class ErrorResponse {
	private int code;
    private String message;
    private String error;

    public ErrorResponse(ErrorCode errorCode){
    	this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String error) {
    	this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.error = error;
    }

}