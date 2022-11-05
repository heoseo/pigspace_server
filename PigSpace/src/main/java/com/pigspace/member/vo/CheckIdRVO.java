package com.pigspace.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CheckIdRVO {

	@Schema(description = "ID 사용가능 여부", example = "Y: 사용가능, N: 사용불가")
	private String checkIdYn;

}
