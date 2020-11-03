package com.models;

public class User {
	
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
	public User (int userId, String username, String password, String firstName, String lastName, String role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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



	public String getPassword() {
		return password;
	}



	public String getFirstName() {
		return firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public String getRole() {
		return role;
	}

}
