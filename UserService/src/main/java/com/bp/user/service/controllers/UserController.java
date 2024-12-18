package com.bp.user.service.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bp.user.service.entities.User;
import com.bp.user.service.services.UserService;
import com.bp.user.service.services.impl.UserServiceImpl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	//create user
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1= userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	//single user get
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId){
		User user= userService.getUser(userId);
		return ResponseEntity.ok(user);
	}
	
	//creating fall back method for circuitbreaker
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		logger.info("Fallback is executed because service is down : ",ex.getMessage());
		User user= new User();
		user.setEmail("dummy@gmail.com");
		user.setName("Dummy");
		user.setAbout("This user is created dummy because some services are down");
		user.setUserId("122345");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		List<User> userList= userService.getAllUser();
		return ResponseEntity.ok(userList);
	}
	
}
