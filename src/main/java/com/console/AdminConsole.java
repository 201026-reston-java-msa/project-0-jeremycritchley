package com.console;

import java.util.ArrayList;
import java.util.List;

import com.dao.AccountDAO;
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
			int accnum = Integer.parseInt(input.nextLine());
			Account acc = accServ.getAccountById(accnum);
			if (acc == null) {
				System.out.println("The Account ID you entered did not exist");
			} else {
				promptWithdraw(acc);
			}
			
		} else if (n == 9) {
			
			System.out.println("Please enter the account ID to deposit to");
			int accnum = Integer.parseInt(input.nextLine());
			Account acc = accServ.getAccountById(accnum);
			if (acc == null) {
				System.out.println("The Account ID you entered did not exist");
			} else {
				promptDeposit(acc);
			}
			
		} else if (n == 10) {
			
			System.out.println("Please enter the transfer source account ID");
			int accnum = Integer.parseInt(input.nextLine());
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
		} else if (n == 13) {
			deleteAccountsByStatus();
		} else {
			super.processInput(n);
		}
	}
	
	
	/*
	 * private method for display accounts by CLOSED status
	 * prompts [y/n] for deletion of all the listed users
	 */
	private void deleteAccountsByStatus() {
		List<Account> accs = accServ.getByStatus(2);
		
		if (accs != null) {
			for (Account a: accs) {
				System.out.println(a.toString());
			}

			String c = "";
			do {
				System.out.println("Are you sure you want to delete the previously listed Account(s)? [y/n]");
				c = input.nextLine();
			} while (!c.equalsIgnoreCase("y") && !c.equalsIgnoreCase("n"));
			
			if (c.equalsIgnoreCase("y")) {	// delete users
				for (Account a: accs) {
					adminServ.removeAccount(a);
				}
			}
			
			
		}
		
	}
	
	/*
	 * Prompt for Account ID of of account to delete
	 * 	- if exists, ask for reassurance and delete
	 */
	private void deleteAccount() {

		System.out.println("Plese enter the Account ID of the account to be deleted");
		
		try {
			int id = Integer.parseInt(input.nextLine());
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

	/*
	 * prompt for ID of user to delete
	 * If user ID exists, ask for reassurance and delete
	 */
	private void deleteUser() {

		System.out.println("Please enter the User ID of the User to delete");
		try {
			int id = Integer.parseInt(input.nextLine());
			User u = empServ.viewByUser(id);
			if (u == null) {
				System.out.println("User with ID " + id + " does not exist");
			} else {
				System.out.println("Are you sure you want to delete: [y/n]\n" + u.toString());
				String c = input.nextLine();
				if (c.equalsIgnoreCase("y")) {
					if(adminServ.removeUser(u)) {
						System.out.println("User successfully deleted");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		System.out.println(count++ + " :\tDelete All Closed Accounts");
	}
	
	@Override
	protected void displayHeader() {
		System.out.println(cur.getFirstName() + "'s ADMIN VIEW");
	}
	
}
