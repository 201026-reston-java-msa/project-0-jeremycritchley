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
import com.models.User;
import com.utils.ConnectionUtil;

public class UserDAO implements GenericDAO<User> {
		
	Connection connection;
	private static Logger log = Logger.getLogger(UserDAO.class);
	
	public UserDAO() {
		this.connection = ConnectionUtil.getConnection();
	}
	
	@Override
	public User create(User t) {
		
		try {
			String sql = "INSERT INTO users (username, password, first_name, last_name, email, role)"
					+ "values (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(6, t.getEmail());
			ps.setString(7, t.getRole());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				t.setUserId(rs.getInt(1));
				log.info("CREATED USER " + t.getUserId());
			} else {
				log.warn("FAILURE TO CREATE USER");
				t = null;
			}
			
			
		} catch (SQLException e) {
			log.warn("FAILURE TO CREATE USER");
			t = null;
		}

		return t;
	}

	@Override
	public User get(int id) {
		User cur = null;
		try {
			String sql = "SELECT * FROM users WHERE users.user_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				cur = new User();
				cur.setUserId(rs.getInt("user_id"));
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setFirstName(rs.getString("first_name"));
				cur.setLastName(rs.getString("last_name"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getString("role"));
			}
			
			
		} catch (SQLException e) {
			log.warn("FAILURE TO RETRIEVE USER " + id);
			cur = null;
		}
		
		return cur;
	}

	@Override
	public User update(User t) {
		try {
			String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setString(6, t.getRole());
			
			if (!ps.execute()) {
				log.warn("FAILURE TO UPDATE USER " + t.getUserId());
				return null;
			}
			log.info("UPDATED USER " + t.getUserId());
		} catch (SQLException e) {
			log.warn("FAILURE TO UPDATE USER " + t.getUserId());
			return null;
		}
		
		return t;
	}

	@Override
	public boolean delete(User t) {
		try {
			// Delete accounts associated with user
			AccountDAO accDao = new AccountDAO();
			List<Account> accs = accDao.getByInt("user_id", t.getUserId());
			for (Account a: accs) {
				if (!accDao.delete(a))
					return false;
			}
			
			// delete user
			String sql = "DELETE FROM users WHERE user_id = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, t.getUserId());
			
			if (ps.executeUpdate() == 0) {
				log.warn("FAILURE TO DELETE USER " + t.getUserId());
				return false;
			}
			
		} catch (SQLException e) {
			log.warn("FAILURE TO DELETE USER " + t.getUserId());
			return false;
		}
		log.info("DELETED USER " + t.getUserId());
		return true;
	}

	@Override
	public List<User> getAll() {
		
		try {
			String sql = "SELECT * FROM users;";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<User> allusers = new ArrayList<User>();
			User cur = null;
			
			while (rs.next()) {
				cur = new User();
				cur.setUserId(rs.getInt("user_id"));
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setFirstName(rs.getString("first_name"));
				cur.setLastName(rs.getString("last_name"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getString("role"));
				
				allusers.add(cur);
			}
			
			return allusers;
		} catch (SQLException e) {
			
		}
		log.warn("FAILURE TO GET ALL USERS");
		return null;
	}

	public User getByString(String key, String val) {
		// TODO Auto-generated method stub
		User cur = null;
		try {
			String sql = "SELECT * FROM users WHERE users." + key + " = ?;";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, val);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				cur = new User();
				cur.setUserId(rs.getInt("user_id"));
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setFirstName(rs.getString("first_name"));
				cur.setLastName(rs.getString("last_name"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getString("role"));
			} else {
				log.warn("FAILURE TO GET USER WHERE " + key + "=" + val);
			}
			
			
		} catch (SQLException e) {
			log.warn("FAILURE TO GET USER WHERE " + key + "=" + val);
		}
		
		return cur;
		
	}

}
