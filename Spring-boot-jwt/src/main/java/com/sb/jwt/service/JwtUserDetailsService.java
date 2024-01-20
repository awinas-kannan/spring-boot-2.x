package com.sb.jwt.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Value("${spring.jwt.secret}")
	private String jwtSecret;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("a0m0rtj".equals(username)) {
			return new User("a0m0rtj", new BCryptPasswordEncoder().encode(jwtSecret), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}