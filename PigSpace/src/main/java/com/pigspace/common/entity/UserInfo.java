package com.pigspace.common.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pigspace.common.support.BaseEntity;
import com.pigspace.common.support.BooleanToYNConverter;
import com.pigspace.common.util.HashUtil;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity(name="USER_INFO")
public class UserInfo extends BaseEntity {

	@Column(unique = true)
	private String mbrNo;

	@Id
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
    private Boolean validYn;

    @Column
    private String userType;

    @Column
    private String joinType;


    @Column(nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean verifiedYn;

    public void setVerifiedYn() {
    	this.verifiedYn = true;
    }

	@Override
	public Integer getId() {
		return HashUtil.makeHash(this.userNm, this.userId, this.phoneNo);
	}

}
