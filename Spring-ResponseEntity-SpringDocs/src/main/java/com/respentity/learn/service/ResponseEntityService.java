package com.respentity.learn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.respentity.learn.beans.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResponseEntityService {

	private List<User> userList;

	@PostConstruct
	public void postConstruct() {
		System.out.println("<ResponseEntityService> <postConstruct>");
		log.debug("<ResponseEntityService> <postConstruct>");
		userList = new ArrayList<>();
		userList.add(new User(1, "Awi", 24, "eee"));
		userList.add(new User(2, "Bala", 24, "eee"));
		userList.add(new User(3, "Fareed", 24, "eee"));
		userList.add(new User(4, "GD", 24, "eee"));
		userList.add(new User(5, "Aravind", 24, "eee"));
		userList.add(new User(6, "Surya", 24, "mech"));
	}

	public Optional<User> getUserById(int id) {

		return userList.stream().filter((e) -> {
			System.out.println("Filter " + e.getId());
			return e.getId() == id;
		}).findFirst();

	}

	public List<User> getAllUsers() {

		return userList;
	}
}
