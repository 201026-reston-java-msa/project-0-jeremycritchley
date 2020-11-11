package com.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.dao.AccountDAO;
import com.models.Account;
import com.services.interfaces.AccountServiceInter;
import com.services.interfaces.AccountsById;

public class AccountService implements AccountServiceInter, AccountsById {

	
	private static Logger log = Logger.getLogger(AccountService.class);
	AccountDAO accDAO;
	
	public AccountService() {
		accDAO = new AccountDAO();
	}
	
	
	@Override
	public boolean withdraw(Account acc, double amount) {

		if (amount > acc.getBalance()) {
			log.warn("OVERDRAW ATTEMPTED AT ACCOUNT " + acc.getAccId());
			return false;
		}
		if (deposit(acc, amount*-1)) {	// call deposit on negative amount, check return val
			log.info("WITHDRAW SUCCESS OF $" + amount + " FROM ACCOUNT " + acc.getAccId());
			return true;
		} else {
			log.warn("WITHDRAW FAILURE AT ACCOUNT " + acc.getAccId() + " AMOUNT $" + amount);
			return false;
		}
	}

	
	@Override
	public boolean deposit(Account acc, double amount) {
		
		acc.setBalance(acc.getBalance()+amount);
		Account c = accDAO.update(acc);
		
		if (c == null) {
			log.warn("DEPOSIT FAILURE AT ACCOUNT " + acc.getAccId() + " AMOUNT $" + amount);
			return false;
		} else if (amount > 0){
			log.info("DEPOSIT SUCCESS OF $" + amount + " TO ACCOUNT " + acc.getAccId());
		}
		
		return true;
	}

	
	@Override
	public boolean transfer(Account srcAcc, Account targetAcc, double amount) {

		if (amount < srcAcc.getBalance()) {
			if (accDAO.transfer(srcAcc, targetAcc, amount)) {
				return true;
			}
		}
		log.warn("TRANSFER FAILURE FROM ACCOUNT " + srcAcc.getAccId() + " TO ACCOUNT " + targetAcc.getAccId() + " OF AMOUNT $" + amount);
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
