package com.dao;

import java.util.List;

import com.dao.interfaces.GenericDAO;
import com.models.Account;

public class AccountDAO implements GenericDAO<Account> {

	@Override
	public Account create(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Account t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Account> getByUser(int userId) {
		return null;
	}

	public List<Account> getByStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

}
