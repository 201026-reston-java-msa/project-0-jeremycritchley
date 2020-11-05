package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.dao.interfaces.GenericDAO;
import com.models.User;
import com.utils.ConnectionUtil;

public class UserDAO implements GenericDAO<User> {
	
	Connection connection;
	
	public UserDAO() {
		this.connection = ConnectionUtil.getConnection();
	}
	
	@Override
	public User create(User t) {
		User u = null;
		try {
			String sql = "";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			
		}

		return null;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User getByString(String username, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
