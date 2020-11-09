package com.services;


import org.apache.log4j.Logger;

import com.dao.UserDAO;
import com.models.User;
import com.services.interfaces.LoginServiceInter;

public class LoginService implements LoginServiceInter {
	
	private static Logger log = Logger.getLogger(LoginService.class);
	UserDAO userd;
	
	public LoginService() {
		userd = new UserDAO();
	}
	
	@Override
	public User login(String username, String password) {
		User u = userd.getByString(username, "username");
		if (u != null) {
			if (!u.getPassword().equals(password)) {
				log.warn("INVALID PASSWORD ENTERED FOR " + u.getUsername());
				u = null;
			}
		}
		return u;
	}

	@Override
	public User register(User u) {
		
		if (u == null)
			return null;
		else if (u.getUsername() == null)
			return null;
		else if (u.getPassword() == null)
			return null;
		else if (u.getFirstName() == null)
			return null;
		else if (u.getLastName() == null)
			return null;
		
		u.setRole("standard");
		u = userd.create(u);
		if (u == null) {
			log.warn("FAILED TO REGISTER NEW USER");
		} else {
			log.info("REGISTERED NEW USER " + u.getUserId());
		}
		
		return u;
	}

	@Override
	public boolean usernameExists(String username) {
		
		User u = userd.getByString(username, "username");
		if (u != null) 
			return true;
		return false;
	}
	
	@Override
	public boolean emailExists(String email ) {
		User u = userd.getByString(email, "email");
		if (u != null) 
			return true;
		return false;
	}


}
