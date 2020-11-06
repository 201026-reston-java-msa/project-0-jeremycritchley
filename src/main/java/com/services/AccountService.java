package com.services;

import java.util.List;

import com.dao.AccountDAO;
import com.models.Account;
import com.services.interfaces.AccountServiceInter;
import com.services.interfaces.AccountsById;

public class AccountService implements AccountServiceInter, AccountsById {

	AccountDAO accDAO;
	public AccountService() {
		accDAO = new AccountDAO();
	}
	
	@Override
	public boolean withdraw(Account acc, double amount) {

		if (amount > acc.getBalance()) {
			return false;
		}
		return deposit(acc, amount*-1);
	}

	@Override
	public boolean deposit(Account acc, double amount) {
		
		acc.setBalance(acc.getBalance()+amount);
		Account c = accDAO.update(acc);
		
		if (c == null)
			return false;
		
		return true;
	}

	@Override
	public boolean transfer(Account srcAcc, Account targetAcc, double amount) {

		if (withdraw(srcAcc, amount)) {
			return deposit(targetAcc, amount);
		}
		return false;
	}

	
	@Override
	public List<Account> getAccountsByUser(int userId) {
		List<Account> accs = accDAO.getByInt("user_id", userId);
		
		return accs;
	}

	@Override
	public Account create(Account acc) {

		acc = accDAO.create(acc);
		return acc;
	}
	
	public List<Account> getByStatus(int n) {
		List<Account> accs = accDAO.getByInt("status", n);
		return accs;
	}

	
}
