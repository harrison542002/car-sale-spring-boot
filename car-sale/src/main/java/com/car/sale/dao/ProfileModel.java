package com.car.sale.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileModel implements ModelAttribute {
	private String lastName;
	private String firstName;
	private String gender;
	private String location;
}
