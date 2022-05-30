package com.pigplace.common.vo;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResponseApiEntity{

	@ApiModelProperty(example = "상태코드")
	private int status;
	
	@ApiModelProperty(example = "메세지")
	private String message;
	
	@ApiModelProperty(example = "응답데이터")
	private Map<String, Object> data;
	
	@ApiModelProperty(example = "시간")
	private LocalDateTime timestamp;
	
	public ResponseApiEntity() {
		this(HttpStatus.OK);
	}

	public ResponseApiEntity(HttpStatus httpStatus) {
		this.status = httpStatus.value();
		this.message = httpStatus.getReasonPhrase();
		this.data = new HashMap<>();
		this.timestamp = LocalDateTime.now();
	}
	
	public void add(String key, Object value) {
		this.data.put(key, value);
	}

}
