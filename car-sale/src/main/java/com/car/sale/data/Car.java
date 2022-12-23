package com.car.sale.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "car")
@Getter
@Setter
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long car_id;
	@Column(name = "Make")
	private String make;
	@Column(name = "Model")
	private String model;
	@Column(name = "registration_year")
	private Integer registration_year;
	@Column(name = "Price_Range")
	private String price_range;
	@Column(name = "status")
	private String status;
	@Lob
	@Column(name = "carImage")
	private byte[] carImage;
	@Column(name = "color")
	private String color;
	@Column(name = "mileage")
	private Integer mileage;
	@Column(name = "enginePower")
	private Integer enginePower;
	@Column(name = "transmission")
	private String transmission;
	@Column(name = "license")
	private Boolean license;
	@Column(name = "fuelType")
	private String fuelType;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "car" , cascade = CascadeType.MERGE)
	private List<Appointment> appointments;
}
