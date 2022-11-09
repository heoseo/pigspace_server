package com.pigspace.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.pigspace.common.support.BooleanToYNConverter;
import com.pigspace.common.util.HashUtil;

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
@EntityListeners(AuditingEntityListener.class)
@Entity(name="USER_INFO")
public class UserInfo implements Persistable<Integer> {

	@CreatedDate
    private LocalDateTime createdDate;

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
		return HashUtil.makeMbrHash(this.userNm, this.userId, this.phoneNo);
	}

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		System.out.println("@@@@@@@@@@@@@getId" + getId());
		System.out.println("@@@@@@@@@@@@@createDate" + createdDate);
		return createdDate == null;
	}

}
