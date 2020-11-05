package com.services;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.models.Account;
import com.models.User;
import com.services.interfaces.AdminServiceInter;

public class AdminService implements AdminServiceInter {

	AccountDAO accDAO;
	UserDAO userd;
	
	
	@Override
	public boolean removeAccount(Account acc) {
		accDAO = new AccountDAO();
		
		return accDAO.delete(acc);
	}

	@Override
	public boolean removeUser(User usr) {

		userd = new UserDAO();
		return userd.delete(usr);
	}

}
