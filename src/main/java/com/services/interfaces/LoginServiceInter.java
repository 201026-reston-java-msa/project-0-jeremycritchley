package com.services.interfaces;

import com.models.User;

public interface LoginServiceInter {
	
	User login();
	
	User register();
	
	boolean logout();
}
