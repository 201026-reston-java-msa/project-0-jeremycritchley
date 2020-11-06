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
import com.models.User;
import com.utils.ConnectionUtil;

public class UserDAO implements GenericDAO<User> {
	
	Connection connection;
	
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
			} else {
				t = null;
			}
			
			
		} catch (SQLException e) {
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
			
			if (!ps.execute())
				return null;
		} catch (SQLException e) {
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
			
			if (ps.executeUpdate() == 0)
				return false;
			
		} catch (SQLException e) {
			return false;
		}
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
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return cur;
		
	}

}
