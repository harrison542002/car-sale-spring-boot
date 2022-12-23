package com.car.sale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.car.sale.dao.Booking;
import com.car.sale.data.Appointment;
import com.car.sale.data.Car;
import com.car.sale.data.User;
import com.car.sale.exception.AppointmentNotFound;
import com.car.sale.repo.AppointmentRepository;
import com.car.sale.repo.CarRepository;
import com.car.sale.status.AppointmentStatus;
import com.car.sale.status.CarStatus;
@Service
public class TransactionRelatedService {

	private final AppointmentRepository appointmentRepository;
	private final CarRepository carRepository;
	public TransactionRelatedService(AppointmentRepository appointmentRepository,
			CarRepository carRepository) {
		this.appointmentRepository = appointmentRepository;
		this.carRepository = carRepository;
	}
	
	//Book new appointment
	public Appointment bookNewAppointment(Booking booking, User user, Car car) {
		Appointment appointment = new Appointment();
		appointment.setBidding_price(booking.getBiddingPrice());
		appointment.setPurpose(booking.getPurpose());
		appointment.setUser(user);
		appointment.setCar(car);
		appointment.setStatus(AppointmentStatus.PENDING.name());
		return appointmentRepository.save(appointment);
	}

	//Approve or Deny booking for test drive
	public boolean modifyBookingStatus(String action, Long bookingId) {
		if(action.equals(AppointmentStatus.DENY.name())) {
			appointmentRepository.deleteById(bookingId);
			return true;
		}
		if(!action.equals(AppointmentStatus.APPROVE.name())) {
			return false;
		}		
		Optional<Appointment> appointment = appointmentRepository.findById(bookingId);
		appointment.orElseThrow(() -> 
			new AppointmentNotFound("Appointment with id " + bookingId + " not Found!"));
		Appointment realAppointment = appointment.get();
		realAppointment.setStatus(action);
		appointmentRepository.save(realAppointment);
		return true;
	}

	//Transaction of Car sale
	public boolean transactCar(String action, Long carId, Long appointmentId) {
		boolean validatedAction = 
			(!action.equals(CarStatus.UNTRANSACT.name()) 
					&& !action.equals(CarStatus.TRANSACT.name())) ? false : null;
		if(action.equals(CarStatus.UNTRANSACT.name())) {
			//Delete this transaction appointment
			appointmentRepository.deleteById(appointmentId);
			validatedAction = true;
		}
		if(action.equals(CarStatus.TRANSACT.name())) {
			Car car = carRepository.findById(carId).get();
			//Delete all appointment related with this car
			car.getAppointments().forEach(appointment -> {
				appointmentRepository.deleteById(appointment.getAppointment_id());
			});
			//Delete this car from car list as it has been transacted.
			carRepository.delete(car);
			validatedAction = true;
		}
		return validatedAction;
	}
	
	public  List<Appointment> getAppointments() {
		return appointmentRepository.findAll();
	}
	
	public Appointment getAppointment(Long appointmentId) {
		return appointmentRepository.findById(appointmentId).get();
	}
	
	//check existence booking
	public Optional<Appointment> checkExistAppointment(Long userId, Long carId) {
		return appointmentRepository.findByUserIdAndCarId(userId, carId);
	}
}
