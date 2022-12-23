package com.car.sale.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.sale.dao.Booking;
import com.car.sale.data.Car;
import com.car.sale.data.User;
import com.car.sale.exception.CarNotFound;
import com.car.sale.exception.ModifyBookingException;
import com.car.sale.security.MyUserDetail;
import com.car.sale.services.CarRelatedService;
import com.car.sale.services.TransactionRelatedService;
import com.car.sale.services.UserRelatedService;

@Controller
@RequestMapping("/transcation")
public class TranscationController {

	private final UserRelatedService userRelatedService;
	private final TransactionRelatedService transactionRelatedService;
	private final CarRelatedService carRelatedService;

	// Inject dependencies
	public TranscationController(UserRelatedService userRelatedService,
			TransactionRelatedService transactionRelatedService, CarRelatedService carRelatedService) {
		this.carRelatedService = carRelatedService;
		this.userRelatedService = userRelatedService;
		this.transactionRelatedService = transactionRelatedService;
	}

	// AJAX post request controller for booking
	@PostMapping("/booking/{carId}")
	@ResponseBody
	private Booking bookForCarTest(Model model, @ModelAttribute("booking") Booking booking,
			@PathVariable("carId") Long carId, Authentication authentication) throws CarNotFound {
		User user = userRelatedService.getUserWithId(((MyUserDetail) authentication.getPrincipal())
				.getUserId());
		Car car = carRelatedService.getCar(carId); 
		transactionRelatedService.bookNewAppointment(booking, user, car);
		return booking;
	}
	
	//AJAX controller for approve or deny booking request
	@GetMapping("/processBooking/{bookingId}")
	@ResponseBody
	private String processBooking(Model model, 
			@RequestParam("action") String action,
			@PathVariable("bookingId") Long bookingId) {
		
		boolean success = transactionRelatedService.modifyBookingStatus(action, bookingId);
		if(!success) {
			throw new ModifyBookingException("Modifying booking with id " + bookingId + " is not succeeded.");
		}
		return action;
	}
	
	//AJAX controller for transaction of car
	@GetMapping("/dealing")
	@ResponseBody
	private String transactCar(Model model,
			@RequestParam("action") String action,
			@RequestParam("carId") Long carId,
			@RequestParam("appointmentId") Long appointmentId) {
	  boolean success =	transactionRelatedService.transactCar(action, carId, appointmentId);
	  if(!success) {
			throw new ModifyBookingException("Modifying booking with id " 
					+ appointmentId + " is not succeeded.");
	  }
	  return action;
	}
}
