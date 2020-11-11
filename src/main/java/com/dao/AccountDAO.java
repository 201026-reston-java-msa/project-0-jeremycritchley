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
			String sql = "INSERT INTO \"Project0\".accounts (user_id, balance, status)"
					+ "values(?,?,?);";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, t.getUserId());
			ps.setDouble(2, t.getBalance());
			ps.setInt(3, t.getStatus());
			
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
			e.printStackTrace();
			log.warn("FAILURE TO CREATE ACCOUNT");
			t = null;
		}
		
		return t;
	}

	@Override
	public Account get(int id) {
		Account a = null;
		try {
			String sql = "SELECT * FROM \"Project0\".accounts WHERE account_id = ?;";
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
			String sql = "UPDATE \"Project0\".accounts SET balance = ?, status = ? WHERE account_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setDouble(1, t.getBalance());
			ps.setInt(2, t.getStatus());
			ps.setInt(3, t.getAccId());
			ps.executeUpdate();
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
			String sql = "DELETE FROM \"Project0\".accounts WHERE account_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, t.getAccId());
			
			ps.executeUpdate();
			
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
			
			String sql = "SELECT * FROM \"Project0\".accounts;";
			
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
			log.warn("FAILURE TO GET ALL ACCOUNTS");
		}
		
		return allaccs;
	}
	
	public List<Account> getByInt(String key, int val) {
		List<Account> accs = null;
		try {
			String sql = "SELECT * FROM \"Project0\".accounts WHERE " + key + " = ?;";
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
		if (accs.size() == 0)
			accs = null;
		return accs;
	}

	public boolean transfer(Account srcAcc, Account targetAcc, double amount) {
		try {
			String sql = "BEGIN;"
					+ "UPDATE \"Project0\".accounts SET balance = ?"
					+ "WHERE account_id = ?;"
					+ "UPDATE \"Project0\".accounts SET balance = ?"
					+ "WHERE account_id = ?;"
					+ "COMMIT;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setDouble(1, srcAcc.getBalance() - amount);
			ps.setInt(2, srcAcc.getAccId());
			ps.setDouble(3, targetAcc.getBalance() + amount);
			ps.setInt(4, targetAcc.getAccId());
			
			ps.executeUpdate();
			log.info("TRANSFER SUCCESS OF AMOUNT $" + amount + " FROM ACCOUNT " 
						+ srcAcc.getAccId() + " TO ACCOUNT " + targetAcc.getAccId());
			return true;
			
		} catch (SQLException e) {
			
		}
		
		log.info("TRANSFER FAILURE OF AMOUNT $" + amount + " FROM ACCOUNT " 
				+ srcAcc.getAccId() + " TO ACCOUNT " + targetAcc.getAccId());
		return false;
	}

}
