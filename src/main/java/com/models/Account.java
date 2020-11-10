package com.models;

public class Account {
	
	private int accId;
	private int userId;
	private double balance;
	private int status;
	
	public Account() {
		super();
	}
	
	public Account(int accId, int userId, double balance, int status) {
		this.accId = accId;
		this.userId = userId;
		this.balance = balance;
		this.status = status;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		String st = "";
		if (status == 0) {
			st = "PENDING";
		} else if (status == 1) {
			st = "OPEN";
		} else if (status == 2) {
			st = "CLOSED";
		}
		
		return "ACCOUNT: " + accId + "\tOWNER: " + userId + "\nSTATUS: " + st + "\tBALANCE: $" + String.format("%.2f", balance);
	}
	
	
	
}
