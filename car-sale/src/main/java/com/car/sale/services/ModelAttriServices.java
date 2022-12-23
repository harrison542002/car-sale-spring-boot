package com.car.sale.services;

import org.springframework.stereotype.Service;

import com.car.sale.dao.Booking;
import com.car.sale.dao.CarModel;
import com.car.sale.dao.ModelAttribute;
import com.car.sale.dao.ProfileModel;
import com.car.sale.dao.UserModel;

@Service
public class ModelAttriServices {
	public ModelAttribute getModelAttribute(String modelName) {
		ModelAttribute modelAttribute = null;
		switch(modelName) {
		case "car":
			modelAttribute = new CarModel();
			break;
		case "user":
			modelAttribute = new UserModel();
			break;
		case "booking":
			modelAttribute = new Booking();
			break;
		case "profile":
			modelAttribute = new ProfileModel();
			break;
		}
		return modelAttribute;
	};
}
