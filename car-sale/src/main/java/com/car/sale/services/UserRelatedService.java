package com.car.sale.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.car.sale.dao.ProfileModel;
import com.car.sale.dao.UserModel;
import com.car.sale.data.Authority;
import com.car.sale.data.Profile;
import com.car.sale.data.User;
import com.car.sale.data.UserAuth;
import com.car.sale.exception.ProfileNotFound;
import com.car.sale.exception.UserNameDuplicated;
import com.car.sale.repo.AuthRepository;
import com.car.sale.repo.ProfileRepository;
import com.car.sale.repo.UserAuthRepo;
import com.car.sale.repo.UserRepository;
import com.car.sale.security.Auth;
@Service
public class UserRelatedService {

	private final UserRepository userRepository;
	private final UserAuthRepo userAuthRepo;
	private final AuthRepository authRepo;
	private final PasswordEncoder passwordEncoder;
	private final ProfileRepository profileRepository;

	public UserRelatedService(UserRepository userRepository, 
			UserAuthRepo userAuthRepo, 
			AuthRepository authRepo,
			ProfileRepository profileRepo,
			PasswordEncoder passwordEncoder) {
		this.profileRepository = profileRepo;
		this.authRepo = authRepo;
		this.userRepository = userRepository;
		this.userAuthRepo = userAuthRepo;
		this.passwordEncoder = passwordEncoder;
	}

	// create new user with user name and password
	public User createUser(UserModel userModel) throws UserNameDuplicated{
		if (userModel == null) {
			return null;
		}
		if(userRepository.findAll().stream().anyMatch(u -> u.getUsername().equals(userModel.getUsername()))) {
			throw new UserNameDuplicated("Username with " + userModel.getUsername() + " already existed!" +
					" Please try with different username.");
		}
		User user = new User();
		user.setUsername(userModel.getUsername());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		user.setEmailaddress(userModel.getEmailAddress());
		userRepository.save(user);
		return user;
	}
	
	public String removeOrAssignAuthority(String status, Long userId) {
		if(status.equals("false")) {
			Authority authority = authRepo.findById(1L).get();
			User user = userRepository.findById(userId).get();
			UserAuth userAuth = new UserAuth();
			userAuth.setAuthority(authority);
			userAuth.setUser(user);
			userAuth = userAuthRepo.save(userAuth);
			System.out.println(user.getUser_id());
			return "true";
		} else {
			userAuthRepo.deleteUserAuthority(userId, 1L);
			return "false";
		}
	}

	// insert normal user authority
	public User setAuth(User user, int... authId) {
		int userAuthId = (authId.length >= 1) ? authId[0] : -1;
		int adminAuthId = (authId.length >= 2) ? authId[1] : -1;
		if (userAuthId > 0) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUser(user);
			userAuth.setAuthority(authRepo.findByAuthId(Auth.USER.getId().longValue()).get());
			userAuthRepo.save(userAuth);
		}
		if (adminAuthId > 0) {
			UserAuth userAuth = new UserAuth();
			userAuth.setUser(user);
			userAuth.setAuthority(authRepo.findByAuthId(Auth.ADMIN.getId().longValue()).get());
			userAuthRepo.save(userAuth);
		}
		return user;
	}

	// retrieve user with id
	public User getUserWithId(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	//Get users
	public List<UserModel> getUsers() {
		return userRepository.findAll().stream()
				.map(user -> castToUserModel(user)).collect(Collectors.toList());
	}
	
	//create profile
	public Profile createProfile(User user, ProfileModel profileModel) {
		Profile profile = Profile.builder()
				.firstName(profileModel.getFirstName())
				.lastName(profileModel.getLastName())
				.location(profileModel.getLocation())
				.gender(profileModel.getGender())
				.user(user).build();
		return profileRepository.save(profile);
	}
	
	//check user profile existence
	public Optional<Profile> checkUserExistence(Long userId){
		return profileRepository.findProfileWithUserId(userId);
	}
	
	//edit profile
	@Transactional
	public Profile editProfile(ProfileModel profileModel, Long profileId) {
		Optional<Profile> optionalProfile = profileRepository.findById(profileId);
		optionalProfile.orElseThrow(() -> new ProfileNotFound("Profile "
				+ "for user was not found, please try again!"));
		Profile profile = optionalProfile.get();
		profile.setFirstName(profileModel.getFirstName());
		profile.setLastName(profileModel.getLastName());
		profile.setGender(profileModel.getGender());
		profile.setLocation(profileModel.getLocation());
		return profileRepository.save(profile);
	}
	
	public Profile getProfile(Long userId) {
		return profileRepository.findById(userId).get();
	}
	
	//cast to profile model
	public ProfileModel castToProfileModel(Profile profile) {
		return new ProfileModel(profile.getLastName(),profile.getFirstName(),
				profile.getGender(), profile.getLocation());
	}
	
	public UserModel castToUserModel(User user) {
		return new UserModel(user.getUsername(), user.getPassword(), user.getEmailaddress(), 
				user.getUserAuths().size() >= 2 ? "true" : "false", user.getUser_id());
	}

	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}
}
