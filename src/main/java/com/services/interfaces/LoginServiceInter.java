package com.services.interfaces;

import com.models.User;

public interface LoginServiceInter {
	
	/*
	 * login with pre-existing user
	 * verifying username and password
	 */
	User login(String username, String password);
	
	/*
	 * register new user in Database and return with updated user id
	 */
	User register(User u);
	
	/*
	 * Check if username already exists in DB
	 */
	boolean usernameExists(String username);

	/*
	 * Check if email already exists in DB
	 */
	boolean emailExists(String email);
}
