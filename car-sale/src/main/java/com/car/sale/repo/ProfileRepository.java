package com.car.sale.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.sale.data.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	@Query("SELECT profile FROM Profile as profile WHERE profile.user.user_id=:userId")
	public Optional<Profile> findProfileWithUserId(@Param("userId") Long userId);
}
