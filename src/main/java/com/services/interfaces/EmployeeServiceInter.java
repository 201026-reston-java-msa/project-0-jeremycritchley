package com.services.interfaces;

import java.util.List;

import com.models.Account;
import com.models.User;

public interface EmployeeServiceInter {

	List<Account> viewAllAccounts();
	
	List<User> viewAllCustomers();
	
	User viewByUser(int userId);
	
	List<Account> viewOpenAccounts();

}
