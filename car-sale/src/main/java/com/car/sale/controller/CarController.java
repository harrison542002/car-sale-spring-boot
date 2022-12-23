package com.car.sale.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.sale.dao.CarModel;
import com.car.sale.data.Appointment;
import com.car.sale.data.Car;
import com.car.sale.data.User;
import com.car.sale.exception.CarNotFound;
import com.car.sale.security.MyUserDetail;
import com.car.sale.services.CarRelatedService;
import com.car.sale.services.TransactionRelatedService;
import com.car.sale.services.UserRelatedService;
import com.car.sale.status.CarStatus;
import com.car.sale.util.ImageUtil;

@Controller
@RequestMapping("/cars")
public class CarController {
	private final CarRelatedService carService;
	private final UserRelatedService userRelatedService;
	private final TransactionRelatedService transactionRelatedService;

	public CarController(CarRelatedService carService, UserRelatedService userRelatedService,
			TransactionRelatedService transactionRelatedService) {
		this.carService = carService;
		this.userRelatedService = userRelatedService;
		this.transactionRelatedService = transactionRelatedService;
	}

	// Posting Car Controller
	@PostMapping("/postingCar") 
	public String postingCar(Model model, @ModelAttribute("car") CarModel carModel,
			@RequestParam("file") MultipartFile image,
			Authentication authentication) {
		MyUserDetail credencedUser = ((MyUserDetail) authentication.getPrincipal());
		User user = userRelatedService.getUserWithId(credencedUser.getUserId());
		carService.createNewCar(carModel, user, image);
		return "redirect:/profile";
	}	
	
	@GetMapping("/postCarForm")
	public String postCarForm(Model model) {
		return "postCar";
	}
	
	//Search Car 
	@GetMapping("/searchCars")
	public String searchCars(Model model, @RequestParam("searchQuery") String searchQuery) {
		int totalCars = carService.getTotalCar();
		if(searchQuery.isBlank()) {
			model.addAttribute("error", "No car result was found");
			return "carShows";
		}
		Pageable pageable = PageRequest.of(0, 8);
		List<Car> cars = carService.searchCar(searchQuery.trim(), pageable);
		if(cars.isEmpty()) {
			model.addAttribute("error", "No car result was found for query : " + searchQuery);
			return "carShows";
		}
		List<String> imageSource = (List<String>) cars.stream()
				.map(car -> Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())))
				.collect(Collectors.toList());
		model.addAttribute("cars", cars);
		model.addAttribute("imageSource", imageSource);
		model.addAttribute("searchQuery", searchQuery);
		int totalPage = totalCars / 8;
		model.addAttribute("currentPage", 0);
		model.addAttribute("totalPage", totalPage);
		return "carShows";
	}

	// Deactivate or activate cars
	@GetMapping("/modifyingCar")
	@ResponseBody
	public String deactivateOrActivateCars(@RequestParam("action") String action, Model model,
			@RequestParam("carId") Integer carId) {
		carService.modifyCar(action, carId);
		return "modification Complete";
	}

	// Get Car Detail
	@GetMapping("/getCar/{carId}")
	public String getSingleCars(@PathVariable("carId") Long carId, Model model, Authentication authentication) throws CarNotFound {
		Car car = carService.getCar(carId);
		Long userId = ((MyUserDetail) authentication.getPrincipal()).getUserId();
		Optional<Appointment> appointment = transactionRelatedService.checkExistAppointment(userId, carId);
		if(appointment.isPresent()) {
			model.addAttribute("appointment", appointment.get());
		}
		List<Car> cars = carService.getCarExceptOne(carId);
		if(cars.size() > 0) {
			Car recar = cars.get(0);
			model.addAttribute("rcarimage", Base64Utils
					.encodeToString(ImageUtil.decompress(recar.getCarImage())));
			model.addAttribute("rcar", recar);
		}
		model.addAttribute("car", car);
		model.addAttribute("carImage",Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())));
		return "singleCar";
	}

	// Get All Cars
	@GetMapping("/getAllCars/{page}")
	public String getAllCars(@PathVariable("page") Integer currentPage, Model model) {
		int totalCars = carService.getTotalCar();
		if (currentPage == null || currentPage * 8 > totalCars ) {
			currentPage = 0;
		}
		int totalPage = totalCars / 8;
		Pageable pageable = PageRequest.of(currentPage, 8);
		List<Car> cars = carService.getCars(pageable)
				.stream().filter(car -> car.getStatus().equals(CarStatus.ACTIVATE.name()))
				.collect(Collectors.toList());
		List<String> imageSource = (List<String>) cars.stream()
				.map(car -> Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())))
				.collect(Collectors.toList());
		model.addAttribute("cars", cars);
		model.addAttribute("imageSource", imageSource);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		return "carShows";
	}

	// Retrieve Cars to display in car list page
	private List<List<Car>> retrieveCars(int currentPage) {
		int totalCars = carService.getTotalCar();
		if (currentPage * 8 > totalCars) {
			currentPage = 0;
		}
		List<List<Car>> carsWithDifferStatus = new ArrayList<List<Car>>();
		Pageable pageable = PageRequest.of(currentPage, 8);
		List<Car> cars = carService.getCars(pageable);
		// Pending Cars
		List<Car> pendingCars = cars.stream().filter(eachCar -> eachCar.getStatus().equals(CarStatus.PENDING.name()))
				.collect(Collectors.toList());
		carsWithDifferStatus.add(pendingCars);
		// Activated Cars
		List<Car> activatedCars = cars.stream().filter(eachCar -> eachCar.getStatus().equals(CarStatus.ACTIVATE.name()))
				.collect(Collectors.toList());
		carsWithDifferStatus.add(activatedCars);
		return carsWithDifferStatus;
	}
}
