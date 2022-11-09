package com.pigspace.common.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pigspace.common.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
//	@Query("SELECT u FROM user_info u WHERE u.user_id = :id AND user_pw = :pw")
//    UserInfo selectUserInfo(@Param("id") String user_id, @Param("pw") String user_pw);

	Optional<UserInfo> findByUserId(String userId);
	Optional<UserInfo> findByUserIdAndValidYn(String userId, boolean validYn);
	Optional<UserInfo> findByMbrNo(String mbrNo);
	Optional<UserInfo> findByUserIdAndUserPw(String userId, String userPw);

}
