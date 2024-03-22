package com.flight.management.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.flight.management.model.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}
