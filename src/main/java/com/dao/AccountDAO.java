package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dao.interfaces.GenericDAO;
import com.models.Account;
import com.utils.ConnectionUtil;

public class AccountDAO implements GenericDAO<Account> {
	
	Connection connection;
	
	public AccountDAO() {
		this.connection = ConnectionUtil.getConnection();
	}
	
	@Override
	public Account create(Account t) {

		try {
			String sql = "INSERT INTO accounts (account_id, user_id, balance, status)"
					+ "values(?,?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, t.getAccId());
			ps.setInt(2, t.getUserId());
			ps.setDouble(3, t.getBalance());
			ps.setInt(4, t.getStatus());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				t.setAccId(rs.getInt(1));
			} else {
				t = null;
			}
		} catch (SQLException e) {
			t = null;
		}
		
		return t;
	}

	@Override
	public Account get(int id) {
		Account a = null;
		try {
			String sql = "SELECT * FROM accounts WHERE aaccount_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				a = new Account();
				a.setAccId(rs.getInt("account_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setBalance(rs.getDouble("balance"));
				a.setStatus(rs.getInt("status"));
			}
			
		} catch (SQLException e) {
			a = null;
		}
		
		return a;
	}

	@Override
	public Account update(Account t) {
		// TODO Auto-generated method stub
		try {
			String sql = "UPDATE accounts SET account_id = ?, user_id = ?, balance = ?, status = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, t.getAccId());
			ps.setInt(2, t.getUserId());
			ps.setDouble(3, t.getBalance());
			ps.setInt(4, t.getStatus());
			if (ps.executeUpdate() == 0) {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
		return t;
	}

	@Override
	public boolean delete(Account t) {

		try {
			String sql = "DELETE FROM accounts WHERE account_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, t.getAccId());
			
			if (ps.executeUpdate() == 0)
				return false;
			
		} catch (SQLException e) {
			return false;
		}
		
		return true;
		
	}

	@Override
	public List<Account> getAll() {
		List<Account> allaccs = null;
		try {
			allaccs = new ArrayList<Account>();
			
			String sql = "SELECT * FROM accounts;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet  rs = ps.executeQuery();
			
			Account a = null;
			while (rs.next()) {
				a = new Account();
				a.setAccId(rs.getInt("account_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setBalance(rs.getDouble("balance"));
				a.setStatus(rs.getInt("status"));
				
				allaccs.add(a);
			}
			
			
		} catch (SQLException e) {
			return null;
		}
		
		return null;
	}
	
	public List<Account> getByInt(String key, int val) {
		List<Account> accs = null;
		try {
			String sql = "SELECT * FROM accounts WHERE " + key + " = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, val);
			
			ResultSet rs = ps.executeQuery();
			Account a = null;
			accs = new ArrayList<Account>();
			
			while (rs.next()) {
				a = new Account();
				a.setAccId(rs.getInt("account_id"));
				a.setUserId(rs.getInt("user_id"));
				a.setBalance(rs.getDouble("balance"));
				a.setStatus(rs.getInt("status"));
				
				accs.add(a);
			}
			
		} catch (SQLException e) {
			accs = null;
		}
		
		return accs;
	}

}
