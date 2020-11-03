package com.services.interfaces;

import java.util.List;

import com.models.Account;
import com.models.User;

public interface EmployeeServiceInter {

	void viewAllAccounts();
	
	void viewAllCustomers();
	
	Account viewByAccount(int accId);
	
	User viewByUser(int userId);
	
	void viewOpenAccounts();

}
