package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dao.interfaces.GenericDAO;
import com.models.Account;
import com.utils.ConnectionUtil;

public class AccountDAO implements GenericDAO<Account> {
	
	Connection connection;
	private static Logger log = Logger.getLogger(AccountDAO.class);
	
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
				log.info("CREATED ACCOUNT " + t.getAccId());
			} else {
				log.warn("FAILURE TO CREATE ACCOUNT");
				t = null;
			}
		} catch (SQLException e) {
			log.warn("FAILURE TO CREATE ACCOUNT");
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
			log.warn("FAILURE TO RETRIEVE ACCOUNT " + id);
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
				log.warn("FAILURE TO UPDATE ACCOUNT " + t.getAccId());
				return null;
			}
			log.info("UPDATED ACCOUNT " + t.getAccId());
		} catch (SQLException e) {
			log.warn("FAILURE TO UPDATE ACCOUNT " + t.getAccId());
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
			
			if (ps.executeUpdate() == 0) {
				log.warn("FAILURE TO DELETE ACCOUNT " + t.getAccId());
				return false;
			}
			
		} catch (SQLException e) {
			log.warn("FAILURE TO DELETE ACCOUNT " + t.getAccId());
			return false;
		}
		
		log.info("DELETED ACCOUNT " + t.getAccId());
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
			
		}
		log.warn("FAILURE TO GET ALL ACCOUNTS");
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
			log.warn("FAILURE TO GET ALL ACCOUNTS WHERE " + key + "=" + val);
			accs = null;
		}
		
		return accs;
	}

}
