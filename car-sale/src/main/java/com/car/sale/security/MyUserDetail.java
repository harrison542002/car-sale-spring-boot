package com.car.sale.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.car.sale.data.User;

public class MyUserDetail implements UserDetails {

	private String username;
	private String password;
	private List<GrantedAuthority> grantedauthorities;
	private Long userId;

	public MyUserDetail(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.userId = user.getUser_id();
		this.grantedauthorities = user.getUserAuths().stream().map(userAuth -> {
			return new SimpleGrantedAuthority(userAuth.getAuthority().getAuth_type());
		}).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedauthorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Long getUserId() {
		return this.userId;
	}

}
