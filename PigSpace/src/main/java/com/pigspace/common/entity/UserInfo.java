package com.pigspace.common.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.pigspace.common.support.BooleanToYNConverter;

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
@Entity(name="USER_INFO")
public class UserInfo {


	@Id
	private String mbrNo;

	@Column(unique = true)
    private String userId;

//    @Column(nullable = false, unique = true, length = 30)
    @Column
    private String userPw;

    @Column
    private String userNm;

    @Column(unique = true)
    private String phoneNo;

//    @Column(nullable = false, length = 100)
    @Column(unique = true)
    private String nickname;

    @Column
    private String profileImg;

    @Column(nullable = false, length = 14)
    private String joinDatetime;

    @Column(nullable = false, length = 14)
    private String firstJoinDatetime;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isValid;

    @Column
    private String userType;

    @Column
    private String joinType;

    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isVerified;

    public void setIsVerified() {
    	this.isVerified = true;
    }

}
