package com.services;


import com.dao.UserDAO;
import com.models.User;
import com.services.interfaces.LoginServiceInter;

public class LoginService implements LoginServiceInter {
	
	UserDAO userd;
	
	public LoginService() {
		userd = new UserDAO();
	}
	
	@Override
	public User login(String username, String password) {
		User u = userd.getByString(username, "username");
		if (u != null) {
			if (!u.getPassword().equals(password)) 
				u = null;
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
		
		return userd.create(u);
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
