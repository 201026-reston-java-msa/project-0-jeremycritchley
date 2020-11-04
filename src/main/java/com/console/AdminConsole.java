package com.console;

import com.models.Account;
import com.models.User;
import com.services.AdminService;

public class AdminConsole extends EmployeeConsole{
	
	AdminService adminServ;
	
	public AdminConsole(User cur) {
		super(cur);
		adminServ = new AdminService();
		
	}
	
	@Override
	protected void processInput(int n) {
		
		if (n == 8) {
			
			System.out.println("Please enter the account ID to withdraw from");
			int accnum = input.nextInt();
			Account acc = accServ.getAccountById(accnum);
			if (acc == null) {
				System.out.println("The Account ID you entered did not exist");
			} else {
				promptWithdraw(acc);
			}
			
		} else if (n == 9) {
			
			System.out.println("Please enter the account ID to deposit to");
			int accnum = input.nextInt();
			Account acc = accServ.getAccountById(accnum);
			if (acc == null) {
				System.out.println("The Account ID you entered did not exist");
			} else {
				promptDeposit(acc);
			}
			
		} else if (n == 10) {
			
			System.out.println("Please enter the transfer source account ID");
			int accnum = input.nextInt();
			Account acc = accServ.getAccountById(accnum);
			if (acc == null) {
				System.out.println("The Account ID you entered did not exist");
			} else {
				promptTransfer(acc);
			}
			
		} else if (n == 11) {
			deleteAccount();
		} else if (n == 12) {
			deleteUser();
		} else {
			super.processInput(n);
		}
	}
	
	
	private void deleteUser() {

		System.out.println("Plese enter the Account ID of the account to be deleted");
		
		try {
			int id = input.nextInt();
			Account acc = accServ.getAccountById(id);
			if (acc == null) {
				System.out.println("Account with ID " + id + " does not exist");
			} else {
				System.out.println("Are you sure you want to delete: [y/n]\n" + acc.toString());
				String c = input.nextLine();
				if (c.equalsIgnoreCase("y")) {
					adminServ.removeAccount(acc);
				}
			}
		} catch (Exception e) {
			System.out.println("Input Mismatch Occured");
		}
		
	}

	private void deleteAccount() {

		System.out.println("Please enter the User ID of the User to delete");
		try {
			int id = input.nextInt();
			User u = empServ.viewByUser(id);
			if (u == null) {
				System.out.println("User with ID " + id + " does not exist");
			} else {
				System.out.println("Are you sure you want to delete: [y/n]\n" + u.toString());
				String c = input.nextLine();
				if (c.equalsIgnoreCase("y")) {
					adminServ.removeUser(u);
				}
			}
		} catch (Exception e) {
			System.out.println("Input Mismatch Occured");
		}
		
	}

	@Override
	protected void displayOptions() {
		super.displayOptions();
		System.out.println(count++ + " :\tWithdraw from specified Account");
		System.out.println(count++ + " :\tDeposit to specific Account");
		System.out.println(count++ + " :\tTransfer to and from specified Accounts");
		System.out.println(count++ + " :\tDelete Account");
		System.out.println(count++ + " :\tDelete User");
	}
	
	@Override
	protected void displayHeader() {
		System.out.println(cur.getFirstName() + "'s ADMIN VIEW");
	}
	
	/*
	 * Print options:
	 * 	- super();
	 * 	- delete account
	 * 	- delete user
	 */
}
