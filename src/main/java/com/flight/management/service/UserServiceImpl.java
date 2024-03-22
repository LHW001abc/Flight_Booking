package com.flight.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flight.management.model.User;
import com.flight.management.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;


	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(userName);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRole());
		UserDetails userDetails= new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
		System.out.println("ROLE: "+userDetails.getAuthorities());
		return userDetails;
	}

	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(String role) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(role);
			authorities.add(tempAuthority);


		return authorities;
	}
}
