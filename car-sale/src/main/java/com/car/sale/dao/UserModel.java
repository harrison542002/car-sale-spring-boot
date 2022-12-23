package com.car.sale.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements ModelAttribute{
	private String username;
	private String password;
	private String emailAddress;
	private String status;
	private Long userId;
}
