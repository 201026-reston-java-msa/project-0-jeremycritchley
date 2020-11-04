package com.services;

import com.models.User;
import com.services.interfaces.LoginServiceInter;

public class LoginService implements LoginServiceInter {

	@Override
	public User login(String username, String password) {
		
		return null;
	}

	@Override
	public User register(User u) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean usernameExists(String username) {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	public boolean emailExists(String email ) {
		return false;
		//TODO
	}

}
