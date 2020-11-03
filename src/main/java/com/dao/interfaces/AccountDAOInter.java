package com.dao.interfaces;

import java.util.List;

import com.models.Account;

public interface AccountDAOInter extends GenericDAO<Account>{
	public List<Account> getByStatus(int status);
}
