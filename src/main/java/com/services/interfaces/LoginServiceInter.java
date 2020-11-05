package com.services.interfaces;

import com.models.User;

public interface LoginServiceInter {
	
	User login(String username, String password);
	
	User register(User u);
	

	boolean usernameExists(String username);

	boolean emailExists(String email);
}
