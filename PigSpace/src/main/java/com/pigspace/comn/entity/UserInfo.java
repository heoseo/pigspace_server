package com.pigspace.comn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

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
@Entity(name="USER_INFO") // ���̺� ���� �ۼ�
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

    @Column
    private String joinDatetime;

    @Column
    private String firstJoinDatetime;

    @Column
    private String validateYn;

    @Column
    private String userType;

    @Column
    private String joinType;

    @Column
    @ColumnDefault("0") //default 0
    private Boolean verified;

    public void setVerified() {
    	this.verified = true;
    }

}
