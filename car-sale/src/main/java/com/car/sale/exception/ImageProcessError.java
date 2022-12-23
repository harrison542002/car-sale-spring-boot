package com.car.sale.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
public class ImageProcessError extends RuntimeException {
	private String errorMsg;

	public ImageProcessError(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
