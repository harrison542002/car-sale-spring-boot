package com.car.sale.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long user_id;
	@Column(name = "password")
	private String password;
	@Column(name = "username")
	private String username;
	@Column(name = "emailaddress")
	private String emailaddress;
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	private List<Car> cars;
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy = "user" , cascade = CascadeType.MERGE)
	private List<Appointment> appointments;
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Profile profile;
	@LazyCollection(LazyCollectionOption.TRUE)
	@ManyToMany(mappedBy = "user", cascade = CascadeType.MERGE)
	private List<UserAuth> userAuths;
}

