package com.services.interfaces;

import java.util.List;

import com.models.Account;

public interface AccountServiceInter {
	/*
	 * check if amount to withdraw is greater than account balance
	 * call deposit on amount * -1
	 */
	boolean withdraw(Account acc, double amount);
	
	/*
	 * Set's balance and updates account
	 * 	- negative values checked in Console to allow for code reuse
	 */
	boolean deposit(Account acc, double amount);
	
	/*
	 * if srcAcc balance is greater than amount
	 * transfer amount from srcAcc to targetAcc
	 */
	boolean transfer(Account srcAcc, Account targetAcc, double amount);

	/*
	 * get all user's accounts by their user id
	 */
	List<Account> getAccountsByUser(int userId);
	
	/*
	 * Create Account acc in DB
	 */
	Account create(Account acc);
	
}
