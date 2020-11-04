package com.services;

import java.util.List;

import com.models.Account;
import com.models.User;
import com.services.interfaces.AccountsById;
import com.services.interfaces.EmployeeServiceInter;

public class EmployeeService implements EmployeeServiceInter, AccountsById {

	@Override
	public List<Account> viewAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> viewAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User viewByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> viewOpenAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
