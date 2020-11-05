package com.services;

import java.util.List;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.models.Account;
import com.models.User;
import com.services.interfaces.AccountsById;
import com.services.interfaces.EmployeeServiceInter;

public class EmployeeService implements EmployeeServiceInter, AccountsById {
	AccountDAO accd;
	UserDAO userd;
	
	@Override
	public List<Account> viewAllAccounts() {
		accd = new AccountDAO();
		
		return accd.getAll();
	}

	@Override
	public List<User> viewAllCustomers() {

		userd = new UserDAO();
		
		return userd.getAll();
	}

	@Override
	public User viewByUser(int userId) {

		userd = new UserDAO();
		return userd.get(userId);
		
	}

	@Override
	public List<Account> viewOpenAccounts() {

		accd = new AccountDAO();
		return accd.getByStatus(0);
	}

	public boolean update(Account a) {
		accd = new AccountDAO();
		a = accd.update(a);
		
		if (a == null)
			return false;
		
		return true;
	}
	
	

}
