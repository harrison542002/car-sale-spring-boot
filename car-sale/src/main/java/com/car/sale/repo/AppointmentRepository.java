package com.car.sale.repo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.car.sale.data.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	@Query("SELECT appointment FROM Appointment as appointment WHERE appointment.user.user_id = :userId AND "
			+ "appointment.car.car_id = :carId")
	public Optional<Appointment> findByUserIdAndCarId(@Param("userId")Long userId, @Param("carId")Long carId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Appointment as appointment WHERE appointment.car.car_id=:carId")
	public void deleteAppointments(@Param("carId") Long carId);
}
