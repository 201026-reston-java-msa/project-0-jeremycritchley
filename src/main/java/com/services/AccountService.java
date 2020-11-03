package com.services;

import com.models.Account;
import com.services.interfaces.AccountServiceInter;

public class AccountService implements AccountServiceInter {

	@Override
	public boolean withdraw(Account acc, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deposit(Account acc, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transfer(Account srcAcc, Account targetAcc, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
