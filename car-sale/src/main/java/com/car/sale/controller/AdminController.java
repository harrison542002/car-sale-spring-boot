package com.car.sale.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.sale.data.Appointment;
import com.car.sale.data.Car;
import com.car.sale.data.Profile;
import com.car.sale.data.User;
import com.car.sale.security.MyUserDetail;
import com.car.sale.services.CarRelatedService;
import com.car.sale.services.TransactionRelatedService;
import com.car.sale.services.UserRelatedService;
import com.car.sale.util.ImageUtil;

@Controller
public class AdminController {

	
	private final UserRelatedService userService;
	private final CarRelatedService carRelatedService;
	private final TransactionRelatedService transactionRelatedService;

	public AdminController(UserRelatedService userService,
			CarRelatedService carRelatedService,
			TransactionRelatedService transactionRelatedService) {
		this.userService = userService;
		this.carRelatedService = carRelatedService;
		this.transactionRelatedService = transactionRelatedService;
	}
	
	@GetMapping("/admin")
	public String displayProfile(
			Model model, 
			Authentication authentication,
			@RequestParam(value = "status", required = false, defaultValue = "ACTIVATE") String status) {
		Long userId = ((MyUserDetail) authentication.getPrincipal()).getUserId();
		User user = userService.getUserWithId(userId);
		Optional<Profile> flexiableProfile = userService.checkUserExistence(userId);
		if (flexiableProfile.isEmpty()) {
			return "createProfile";
		}
		Profile profile = flexiableProfile.get();
		
		if(status.equals("BOOKING")) {
			List<Car> cars = new ArrayList<Car>();
			List<String> imageSources = new ArrayList<String>();
			user.getAppointments().forEach(appointment -> {
				System.out.println(appointment.getBidding_price());
			});
			List<Appointment> appointments = transactionRelatedService.getAppointments();
			appointments.stream()
			.forEach(appointment -> {
				Car car = appointment.getCar();
				cars.add(car);
				imageSources.add(Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())));
			});
			System.out.println(appointments.size());
			model.addAttribute("appointments", appointments);
			model.addAttribute("profile", profile);
			model.addAttribute("imageSources" ,imageSources);
			model.addAttribute("status", status);
			model.addAttribute("cars", cars);
			return "admin";
		}
		
		List<Car> cars = carRelatedService
				.retrieveCarWithCarStatus(status);
		List<String> imageSources = cars.stream()
				.map(car ->   Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())))
				.collect(Collectors.toList());
		model.addAttribute("profile", profile);
		model.addAttribute("imageSources" ,imageSources);
		model.addAttribute("status", status);
		model.addAttribute("cars", cars);
		return "admin";
	}
	
	@GetMapping("/assignAuthority")
	@ResponseBody
	public String assignAuth(@RequestParam("status") String status, 
			@RequestParam("userId") Long userId) {
		return userService.removeOrAssignAuthority(status, userId);
	}
}
