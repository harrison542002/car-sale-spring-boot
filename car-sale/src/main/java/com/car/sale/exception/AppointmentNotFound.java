package com.car.sale.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentNotFound extends NotFoundItem {
	private String errorMsg;
	public AppointmentNotFound(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
