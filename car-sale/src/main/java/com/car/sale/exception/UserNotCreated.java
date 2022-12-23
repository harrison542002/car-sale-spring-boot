package com.car.sale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class UserNotCreated extends RuntimeException {
	private String errorMsg;
	public UserNotCreated(String error) {
		this.errorMsg = error;
	}
}
