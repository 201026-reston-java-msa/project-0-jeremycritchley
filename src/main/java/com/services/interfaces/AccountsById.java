package com.services.interfaces;

import com.dao.AccountDAO;
import com.models.Account;

public interface AccountsById {
	
	/*
	 * get account by account id
	 */
	default Account getAccountById(int id) {
		AccountDAO accd = new AccountDAO();

		return accd.get(id);
	}
	
}
