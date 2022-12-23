package com.car.sale.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.sale.data.UserAuth;

@Repository
public interface UserAuthRepo extends JpaRepository<UserAuth, Long>{
	@Modifying
	@Transactional
	@Query("DELETE FROM UserAuth as userAuth WHERE userAuth.user.user_id=:userId AND userAuth.authority.authId=:authId")
	public void deleteUserAuthority(@Param("userId") Long userId, @Param("authId") Long authId);
}
