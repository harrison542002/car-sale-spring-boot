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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.sale.dao.ProfileModel;
import com.car.sale.dao.UserModel;
import com.car.sale.data.Appointment;
import com.car.sale.data.Car;
import com.car.sale.data.Profile;
import com.car.sale.data.User;
import com.car.sale.exception.UserNameDuplicated;
import com.car.sale.security.Auth;
import com.car.sale.security.MyUserDetail;
import com.car.sale.services.CarRelatedService;
import com.car.sale.services.TransactionRelatedService;
import com.car.sale.services.UserRelatedService;
import com.car.sale.status.CarStatus;
import com.car.sale.util.ImageUtil;

@Controller
public class UserController {

	private final UserRelatedService userService;
	private final CarRelatedService carRelatedService;
	private final TransactionRelatedService transactionRelatedService;

	public UserController(UserRelatedService userService, CarRelatedService carRelatedService,
			TransactionRelatedService transactionRelatedService) {
		this.userService = userService;
		this.carRelatedService = carRelatedService;
		this.transactionRelatedService = transactionRelatedService;
	}
	
	@GetMapping("/contact")
	public String displayContactPage() {
		return "contact";
	}
	
	@GetMapping("/about")
	public String displayAboutPage() {
		return "about";
	}

	@GetMapping("/profile")
	public String displayProfile(HttpServletRequest request, Model model, Authentication authentication,
			@RequestParam(value = "status", required = false, defaultValue = "ACTIVATE") String status) {
		Long userId = ((MyUserDetail) authentication.getPrincipal()).getUserId();
		User user = userService.getUserWithId(userId);
		Optional<Profile> flexiableProfile = userService.checkUserExistence(userId);
		
		if (flexiableProfile.isEmpty()) {
			return "createProfile";
		}
		Profile profile = flexiableProfile.get();
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		if (status.equals("BOOKING")) {
			List<Car> cars = new ArrayList<Car>();
			List<String> imageSources = new ArrayList<String>();
			List<Appointment> appointments = user.getAppointments();
			appointments.stream().forEach(appointment -> {
				Car car = appointment.getCar();
				cars.add(car);
				imageSources.add(Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())));
			});
			model.addAttribute("appointments", appointments);
			model.addAttribute("profile", profile);
			model.addAttribute("imageSources", imageSources);
			model.addAttribute("status", status);
			model.addAttribute("cars", cars);
			return "profile";
		}
		List<Car> cars = carRelatedService.retrieveCarWithCarStatus(status).stream()
				.filter(car -> car.getUser().getUser_id() == userId).collect(Collectors.toList());
		List<String> imageSources = cars.stream()
				.map(car -> Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())))
				.collect(Collectors.toList());
		model.addAttribute("profile", profile);
		model.addAttribute("imageSources", imageSources);
		model.addAttribute("status", status);
		model.addAttribute("cars", cars);
		return "profile";
	}

	@PostMapping("/profile/edit/{profileId}")
	@ResponseBody
	public ProfileModel editProfile(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("gender") String gender,
			@RequestParam("location") String location, @PathVariable("profileId") Long profileid) {
		ProfileModel profileModel = new ProfileModel(lastName, firstName, gender, location);
		userService.editProfile(profileModel, profileid);
		return profileModel;
	}
	
	@GetMapping("/getUsers")
	@ResponseBody
	public List<UserModel> getUsers(){
		return userService.getUsers();
	}

	@GetMapping("/home")
	public String homePage() {
		return "index";
	}

	// Login page
	@GetMapping("/login")
	public String loginPage(Model model) {
		return "login";
	}

	@GetMapping("/registration")
	public String registerPage() {
		return "registration";
	}

	// Controller for registration
	@PostMapping("/registration")
	public String createNewUser(@ModelAttribute("user") UserModel userModel, Model model) 
			throws UserNameDuplicated {
		if(userModel.getEmailAddress().length() <= 0 || userModel.getPassword().length() <= 0 || 
				userModel.getUsername().length() <= 0) {
			model.addAttribute("error", "true");
			return "registration";
		}
		User user = userService.createUser(userModel);
		userService.setAuth(user, Auth.USER.getId());
		return "redirect:/login?register";
	}
	
	@PostMapping("/profile")
	public String createProfile(@ModelAttribute("profile") ProfileModel profileModel,
			Authentication authentication) {
		Long userId = ((MyUserDetail) authentication.getPrincipal()).getUserId();
		User user = userService.getUserWithId(userId);
		userService.createProfile(user, profileModel);
		return "redirect:/profile";
	}
}