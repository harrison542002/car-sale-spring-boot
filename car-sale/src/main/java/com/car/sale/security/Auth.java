package com.car.sale.security;

import lombok.Getter;

@Getter
public enum Auth {
	ADMIN(1),
	USER(2);
	
	private Integer id;
	Auth(Integer id){
		this.id = id;
	}
}
