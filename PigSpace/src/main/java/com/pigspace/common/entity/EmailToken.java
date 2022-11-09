package com.pigspace.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.pigspace.common.util.DateUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="EMAIL_TOKEN")
public class EmailToken {

	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 36)
	private String id;

	@Column(nullable = false, length = 14)
	private String expirationDatetime;

	@Column
	private String verifyType;

	@Column
	private String mbrNo;


	public static EmailToken createEmailToken(String mbrNo, int expiredDay, String verifyType) {
		EmailToken emailToken = new EmailToken();
		//TODO 추후 메일인증 또 필요하면 final 변수 추가 선언 필요
		emailToken.expirationDatetime = DateUtil.getCurrentTimePlusDays("yyyyMMddHHmmss", expiredDay);
		emailToken.mbrNo = mbrNo;
		emailToken.verifyType = verifyType;

		return emailToken;
	}


}