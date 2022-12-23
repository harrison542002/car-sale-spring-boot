package com.car.sale.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.car.sale.dao.CarModel;
import com.car.sale.data.Car;
import com.car.sale.data.User;
import com.car.sale.exception.CarNotFound;
import com.car.sale.exception.ImageProcessError;
import com.car.sale.repo.AppointmentRepository;
import com.car.sale.repo.CarRepository;
import com.car.sale.status.CarStatus;
import com.car.sale.util.ImageUtil;

@Service
public class CarRelatedService {

	private final CarRepository carRepository;
	private final AppointmentRepository appointmentRepository;

	public CarRelatedService(CarRepository carRepository, AppointmentRepository appointmentRepository) {
		this.carRepository = carRepository;
		this.appointmentRepository = appointmentRepository;
	}

	// new car first posted by user
	public Car createNewCar(CarModel carModel, User user, MultipartFile file) {
		Car car = new Car();
		car.setMake(carModel.getMake());
		car.setModel(carModel.getModel());
		car.setPrice_range(carModel.getPriceRange());
		car.setRegistration_year(carModel.getRegsitrationYear());
		car.setStatus(CarStatus.PENDING.name());
		car.setUser(user);
		car.setColor(carModel.getColor());
		car.setMileage(carModel.getMileage());
		car.setEnginePower(carModel.getEnginePower());
		car.setTransmission(carModel.getTransmission());
		car.setLicense(carModel .getLicense());
		car.setFuelType(carModel.getFuelType());
		try {
			car.setCarImage(ImageUtil.compress(file.getBytes()));
			return carRepository.save(car);
		} catch (Exception e) {
			throw new ImageProcessError("Image processing failed, please retry again!");
		}
	}

	// modify status of car
	@Transactional
	public Car modifyCar(String action, Integer carId) {
		if (action.equals(CarStatus.ACTIVATE.name())) {
			return editCarStatus(CarStatus.ACTIVATE, carId);
		}
		appointmentRepository.deleteAppointments(carId.longValue());
		Car car = carRepository.findById(carId.longValue()).get();
		if(car.getAppointments().size() == 0) {
			carRepository.deleteById(carId.longValue());
		}
		return null;
	}

	// get car with car id
	public Car getCar(Long carId) throws CarNotFound {
		Optional<Car> optionalCar = carRepository.findById(carId);
		optionalCar.orElseThrow(() -> new CarNotFound("Car with " + carId + " not found!"));
		return optionalCar.get();
	}

	// get cars
	public List<Car> getCars(Pageable pageable) {
		return carRepository.findAll(pageable).toList();
	}
	
	// retrieve car with car status
	public List<Car> retrieveCarWithCarStatus(String status){
		return carRepository.findByStatus(status);
	}
	
	//Cast Car to CarModel
	public CarModel castToCarModel(Car car) {
		CarModel carModel = new CarModel(car.getMake(), car.getModel(), car.getRegistration_year(), 
				car.getPrice_range(), car.getColor(), car.getMileage(), 
				car.getEnginePower(), car.getTransmission(), car.getLicense(), car.getFuelType(),
				Base64Utils.encodeToString(ImageUtil.decompress(car.getCarImage())),
				car.getCar_id());
		return carModel;
	}

	//edit status of car
	private Car editCarStatus(CarStatus carStatus, Integer carId) {
		Optional<Car> car = carRepository.findById(carId.longValue());
		car.orElseThrow(() -> new CarNotFound("car with id " + carId + "not found!"));
		Car realCar = car.get();
		realCar.setStatus(carStatus.name());
		return carRepository.save(realCar);
	}
	
	public List<Car> getCarExceptOne(Long carId){
		return carRepository.findExceptOne(carId);
	}

	public int getTotalCar() {
		return (int) carRepository.count();
	}
	
	public List<Car> searchCar(String query, Pageable pageable) {
		return carRepository.searchCar(query, pageable);
	}
}
