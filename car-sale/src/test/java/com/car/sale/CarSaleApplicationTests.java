package com.car.sale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import javax.persistence.Transient;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.car.sale.dao.ProfileModel;
import com.car.sale.data.Appointment;
import com.car.sale.data.Authority;
import com.car.sale.data.Car;
import com.car.sale.data.Profile;
import com.car.sale.data.User;
import com.car.sale.data.UserAuth;
import com.car.sale.security.MyUserDetail;
import com.car.sale.services.CarRelatedService;
import com.car.sale.services.TransactionRelatedService;
import com.car.sale.services.UserRelatedService;
import com.car.sale.status.AppointmentStatus;
import com.car.sale.status.CarStatus;

@SpringBootTest
@AutoConfigureMockMvc
class CarSaleApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CarRelatedService carRelatedService;
	@Autowired
	private UserRelatedService userRelatedService;
	@Autowired
	private TransactionRelatedService transactionRelatedService;
	
	@Disabled
	@Test
	@DisplayName("Test for user without admin authority")
	public void testForUserWithoutAdminAuthority() throws Exception {
		Authority authority = new Authority();
		authority.setAuthId(2L);
		authority.setAuth_type("ROLE_USER"); // set only user authority.
		UserAuth userAuth = new UserAuth();
		userAuth.setAuth_user_id(1L);
		userAuth.setAuthority(authority);
		authority.setUserAuths(List.of(userAuth));
		User user = new User();
		user.setPassword("password");
		user.setUser_id(1L);
		user.setUsername("harris");
		user.setUserAuths(List.of(userAuth));
		userAuth.setUser(user);
		mockMvc.perform(get("/admin").with(user(new MyUserDetail(user)))).andExpect(
				status().isForbidden()); //expect status as forbidden code
	}
	
	@Disabled
	@Test
	@DisplayName("Test for user with admin authority")
	public void testForUserWithAdminAuthority() throws Exception {
		Authority authority = new Authority();
		authority.setAuthId(1L);
		authority.setAuth_type("ROLE_ADMIN"); // set admin authority.
		UserAuth userAuth = new UserAuth();
		userAuth.setAuth_user_id(1L);
		userAuth.setAuthority(authority);
		authority.setUserAuths(List.of(userAuth));
		User user = new User();
		user.setPassword("password");
		user.setUser_id(1L);
		user.setUsername("harris");
		user.setUserAuths(List.of(userAuth));
		userAuth.setUser(user);
		mockMvc.perform(get("/admin").with(user(new MyUserDetail(user)))).andExpect(
				status().isOk()); 
		//expect status as ok code
	}
	
	@Disabled
	@Test
	@DisplayName("Data integration testing for editing profile")
	public void testForDataIntegrationWithEditingData() {
		Profile originalProfile = userRelatedService.getProfile(1L); //retrieve original profile
		System.out.println("Original First Name : " + originalProfile.getFirstName());
		ProfileModel profile = 
				new ProfileModel("Aung", "Thiha", "Male", "Singapore"); 
		userRelatedService
			.editProfile(profile, 1L); //edited profile information with profile model class
		originalProfile = userRelatedService.getProfile(1L); //retrieve edited profile
		assertEquals("Thiha", originalProfile.getFirstName()); // compare with inserted data
		System.out.println("After edited First Name : " + originalProfile.getFirstName());
	}
	
	@Disabled
	@Test
	@DisplayName("Modify car status")
	public void testForModificationOfCarStatus() {
		Integer carId = 7; //desirable car Id
		Car car = carRelatedService.getCar(carId.longValue()); //retrieve car entity
		System.out.println("Original Status : " + car.getStatus());
		car = carRelatedService
				.modifyCar(CarStatus.ACTIVATE.name(), carId); //retrieve car entity after modification
		System.out.println("After modified Status : " + car.getStatus());
		assertEquals(CarStatus.ACTIVATE.name(), car.getStatus());
	}
	
	@Test
	@DisplayName("Modify appointment status")
	public void testForModificatioOfAppointmentStatus() {
		Integer appointmentId = 7;
		transactionRelatedService // modify appointment status to APPROVE from PENDING
			.modifyBookingStatus(AppointmentStatus.APPROVE.name(),appointmentId.longValue());
		Appointment appointment = transactionRelatedService
				.getAppointment(appointmentId.longValue()); //get entity to assess after modification
		assertEquals(AppointmentStatus.APPROVE.name(), appointment.getStatus());
	}
}
