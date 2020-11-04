package com.services;

import java.util.List;

import com.models.Account;
import com.services.interfaces.AccountServiceInter;
import com.services.interfaces.AccountsById;

public class AccountService implements AccountServiceInter, AccountsById {

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

	
	@Override
	public List<Account> getAccountsByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

}
