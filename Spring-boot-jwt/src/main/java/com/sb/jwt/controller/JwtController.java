package com.sb.jwt.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sb.jwt.model.JwtRequestDto;
import com.sb.jwt.model.JwtResponseDto;
import com.sb.jwt.service.JwtUserDetailsService;
import com.sb.jwt.util.Constants;
import com.sb.jwt.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JwtController {

	@Value("${spring.jwt.secret}")
	private String secret;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponseDto> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) {
		log.info("{}  ",authenticationRequest);
		if (authenticationRequest.getSecretKey().equals(secret)) {
			log.info("1 {}");
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getSecretKey());
			log.info("2 {}");
			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			log.info("3 {}",userDetails);
			final String token = jwtUtil.generateToken(userDetails);
			log.info("4 {}",token);
			return new ResponseEntity<JwtResponseDto>(new JwtResponseDto(token, "",
					jwtUtil.getExpirationDateFromToken(token), new Date(System.currentTimeMillis())), HttpStatus.OK);
		}
		return new ResponseEntity<JwtResponseDto>(new JwtResponseDto("", Constants.INVALID_SECRET, null, null),
				HttpStatus.UNAUTHORIZED);
	}

	private void authenticate(String username, String secretKey) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, secretKey));
	}
}
