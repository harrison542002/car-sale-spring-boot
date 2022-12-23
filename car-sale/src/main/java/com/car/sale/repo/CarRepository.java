package com.car.sale.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.sale.data.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
	
	@Query("SELECT DISTINCT car FROM Car car WHERE CONCAT(car.model, car.make, car.registration_year, car.price_range) LIKE %:query%")
	public List<Car> searchCar(@Param("query") String query, Pageable pageable);

	public List<Car> findByStatus(String status);
	
	@Query("SELECT car FROM Car as car WHERE car.user.user_id = :user_id")
	public List<Car> findByUser_id(@Param("user_id") Long user_id); 
	
	@Query("SELECT car FROM Car as car WHERE car.car_id <> :carId AND car.status = 'ACTIVATE'")
	public List<Car> findExceptOne(@Param("carId") Long carId); 
}
