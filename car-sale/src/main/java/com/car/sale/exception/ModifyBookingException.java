package com.car.sale.exception;

import lombok.Getter;

@Getter
public class ModifyBookingException extends NotFoundItem  {
	private String errorMsg;
	public ModifyBookingException(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
