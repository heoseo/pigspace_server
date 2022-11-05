package com.pigspace.common.support;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"INTER SERVER ERROR")
    ;

    private int code;
    private String message;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}