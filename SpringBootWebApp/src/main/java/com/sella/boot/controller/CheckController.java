package com.sella.boot.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CheckController {

	@RequestMapping(value = "check")
	public ModelAndView check() {
		return null;

	}

//	Request Header

	@RequestMapping("/listHeaders")
	public void listAllHeaders(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
		});

	}
//Http headers
//HttpHeaders implements MultiValueMap<String, String>
	
	@RequestMapping("/listHttpHeaders")
	public void listHTTPHeaders(@RequestHeader HttpHeaders headers) {
		System.out.println(headers.get("name"));
		headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
		});

	}
}
