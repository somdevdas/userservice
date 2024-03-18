package com.user.service.service;

import java.util.List;

import com.user.service.entity.User;

public interface UserService {

	//create
	User saveUser(User user);
	
	//get all user
	List<User> geAllUsers();
	
	//get single user by user id
	User getUser(String userId);
}
