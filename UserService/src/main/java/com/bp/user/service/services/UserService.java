package com.bp.user.service.services;

import java.util.List;

import com.bp.user.service.entities.User;

public interface UserService {
	
	//user operations
	
	//create
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userID
	User getUser(String userId);
	
	//TODO: delete
	//TODO update
	
}
