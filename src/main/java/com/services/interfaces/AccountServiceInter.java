package com.services.interfaces;

import java.util.List;

import com.models.Account;

public interface AccountServiceInter {
	
	boolean withdraw(Account acc, double amount);
	
	boolean deposit(Account acc, double amount);
	
	boolean transfer(Account srcAcc, Account targetAcc, double amount);

	List<Account> getAccountsByUser(int userId);
	
	Account create(Account acc);
	
}
