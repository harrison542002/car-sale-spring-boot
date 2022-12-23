package com.car.sale.exception;

import lombok.Getter;

@Getter
public class CarNotFound extends NotFoundItem  {
	private String errorMsg;
	public CarNotFound(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
}
