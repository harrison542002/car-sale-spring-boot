package com.car.sale.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking implements ModelAttribute{
	private String purpose;
	private Integer biddingPrice;
}
