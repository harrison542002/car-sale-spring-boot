package com.car.sale.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car.sale.data.Authority;

@Repository
public interface AuthRepository extends JpaRepository<Authority, Long>{
	Optional<Authority> findByAuthId(Long id);
}
