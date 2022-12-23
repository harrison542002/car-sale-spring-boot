package com.car.sale.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModel implements ModelAttribute{
	private String make;
	private String model;
	private Integer regsitrationYear;
	private String priceRange;
	private String color;
	private Integer mileage;
	private Integer enginePower;
	private String transmission;
	private Boolean license;
	private String fuelType;
	private String imageSource;
	private Long carId;
}
