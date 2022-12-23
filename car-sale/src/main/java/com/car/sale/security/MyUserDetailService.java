package com.car.sale.security;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.car.sale.data.User;
import com.car.sale.repo.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	private final UserRepository userRepository;
	
	public MyUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user =  userRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User" + username + " Not Found"));
		return new MyUserDetail(user.get());
		
	}

}
