package com.car.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.car.sale.dao.Booking;
import com.car.sale.dao.CarModel;
import com.car.sale.dao.ProfileModel;
import com.car.sale.dao.UserModel;
import com.car.sale.data.User;
import com.car.sale.exception.UserNameDuplicated;
import com.car.sale.services.ModelAttriServices;

@ControllerAdvice
public class PrerequisiteForController {

	private final ModelAttriServices modelAttriServices;
	public PrerequisiteForController(ModelAttriServices modelAttriServices) {
		this.modelAttriServices = modelAttriServices;
	}
	
	@ModelAttribute("car")
	public CarModel car() {
		return (CarModel) modelAttriServices.getModelAttribute("car");
	}
	
	@ModelAttribute("user")
	public UserModel user() {
		return (UserModel) modelAttriServices.getModelAttribute("user");
	}
	
	@ModelAttribute("booking")
	public Booking booking() {
		return (Booking) modelAttriServices.getModelAttribute("booking");
	}
	
	@ModelAttribute("profile")
	public ProfileModel profile() {
		return (ProfileModel) modelAttriServices.getModelAttribute("profile");
	}
	
	@ExceptionHandler(UserNameDuplicated.class)
	@GetMapping("/reRegister")
	public String handleNotStore(
			HttpServletRequest request,
			UserNameDuplicated userNameDuplicated,
			Model model) {
		request.setAttribute("user", new UserModel());
		model.addAttribute("notFound", userNameDuplicated.getErrorMsg());
		return "registration";
	}
}
