package com.models;

public class Account {
	
	private int accId;
	private int userId;
	private double balance;
	private int status;
	
	public Account(int accId, int userId, double balance, int status) {
		this.accId = accId;
		this.userId = userId;
		this.balance = balance;
		this.status = status;
	}
	
	public int getAccId() {
		return accId;
	}
	public int getUserId() {
		return userId;
	}
	public double getBalance() {
		return balance;
	}
	public int getStatus() {
		return status;
	}
	
	
}
