package com.services.interfaces;

import com.models.Account;

public interface AccountServiceInter {
	
	boolean withdraw(Account acc, double amount);
	
	boolean deposit(Account acc, double amount);
	
	boolean transfer(Account srcAcc, Account targetAcc, double amount);
	
}
