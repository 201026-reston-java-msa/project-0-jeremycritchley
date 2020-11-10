package com.models;

public class User {
	
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	
	public User() {
		super();
	}
	
	public User (int userId, String username, String password, String firstName, String lastName, String email,  String role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (role.toLowerCase().equals("admin")) {
			sb.append("ADMIN\t");
		} else if (role.toLowerCase().equals("employee")) {
			sb.append("EMPLOYEE\t");
		}
		sb.append(username + "\t" + userId + "\n");
		sb.append(firstName + "\t" + lastName + "\n");
		sb.append("email: " + email + "\t" + "password: " + password + "\n");
		return sb.toString();
	}
	
	
}
