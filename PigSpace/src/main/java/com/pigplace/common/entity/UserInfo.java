package com.pigplace.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="USER_INFO") // 테이블 명을 작성
public class UserInfo {

	@Column
	private String mbrNo;

	@Column
    private String userId;

//    @Column(nullable = false, unique = true, length = 30)
    @Column
    private String userPw;

    @Column
    private String userNm;

    @Id
    private String phoneNo;

//    @Column(nullable = false, length = 100)
    @Column
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

}
