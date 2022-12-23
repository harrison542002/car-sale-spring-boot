package com.car.sale.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
public class UserNameDuplicated extends RuntimeException {
	private String errorMsg;
	public UserNameDuplicated(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
