package com.pigplace.comn.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailToken {

	private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(length = 36)
	private String id;

	private LocalDateTime expirationDate;

	private boolean expired;

	private String mbrNo;


	public static EmailToken createEmailToken(String mbrNo) {
		EmailToken emailToken = new EmailToken();
		emailToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE);
		emailToken.expired = false;
		emailToken.mbrNo = mbrNo;

		return emailToken;
	}


	public void setTokenToUsed() {
		this.expired = true;
	}


}