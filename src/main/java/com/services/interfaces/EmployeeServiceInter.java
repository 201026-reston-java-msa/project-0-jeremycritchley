package com.services.interfaces;

import java.util.List;

import com.models.Account;
import com.models.User;

public interface EmployeeServiceInter {

	/*
	 * Retrieve and return all accounts in DB
	 */
	List<Account> viewAllAccounts();
	
	/*
	 * Retrieve and return all users in DB
	 */
	List<User> viewAllCustomers();
	
	/*
	 * retrieve and return user by user id
	 */
	User viewByUser(int userId);
	
	/*
	 * view all accounts with status OPEN
	 */
	List<Account> viewOpenAccounts();

}
