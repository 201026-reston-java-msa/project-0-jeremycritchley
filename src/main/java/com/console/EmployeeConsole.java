package com.console;

import java.util.List;

import com.models.Account;
import com.models.User;
import com.services.EmployeeService;

public class EmployeeConsole extends StandardConsole{
	
	EmployeeService empServ;
	
	public EmployeeConsole(User u) {
		super(u);
		empServ = new EmployeeService();	

	}
	
	@Override
	protected void processInput(int n) {
		if (n == 3) {
			
			List<User> users = empServ.viewAllCustomers();
			for (User u: users) {
				System.out.println();
				System.out.println(u.toString());
			}
			
		} else if (n == 4) {
			
			System.out.println("Please enter the UserID of the user you wish to view");
			System.out.println();
			int userId = Integer.parseInt(input.nextLine());
			User u = empServ.viewByUser(userId);
			if (u == null) {
				System.out.println("Invalid UserID");
			} else {
				List<Account> accs = accServ.getAccountsByUser(u.getUserId());
				System.out.println(u.toString());
				if (accs != null) {
					for (Account a: accs) {
						System.out.println(a.toString());
						System.out.println();
					}
				}
			}
			
		} else if (n == 5) {
			
			List<Account> accs = empServ.viewAllAccounts();
			for (Account a: accs) {
				System.out.println();
				System.out.println(a.toString());
			}
			
		} else if (n == 6) {
			
			System.out.println("Please enter the AccountID of the account you wish to view");
			int accId = Integer.parseInt(input.nextLine());
			Account a = accServ.getAccountById(accId);
			if (a == null) {
				System.out.println("Invalid AccountID");
			} else {
				System.out.println(a.toString());
			}
			
		} else if (n == 7) {
			
			approveOrDeny();
			
		} else {
			super.processInput(n);
		}
	}
	
	/*
	 * List Accounts one-by-one with status PENDING
	 * after each account Prompt
	 * 	1. Approve
	 * 	2. Deny
	 * 	0. return to home console
	 * respectively, approve or deny account
	 * or exit process if 0
	 */
	private void approveOrDeny() {
		
		
		List<Account> accs = accServ.getByStatus(0);
		
		if (accs != null) {
			System.out.println("Listing Pending Accounts to Approve or Deny\nenter 0 to exit");
			
			for (Account a : accs) {
				
				int c = -1;
				do {
					System.out.println(a);
					System.out.println("Enter:\n\t1 to Approve\n\t2 to Deny\n\t0 to exit");
					a.toString();
					try {
						c = Integer.parseInt(input.nextLine());
					} catch (Exception e) {
						System.out.println("Please enter a Number");
					}
				} while (c < 0 || c > 2);
				if (c == 1) {
					a.setStatus(1);
					if (empServ.update(a)) {
						System.out.println("Account " + a.getAccId() + " is now Open");
					} else {
						System.out.println("A problem occured while trying to Open Account " + a.getAccId());
					}
					System.out.println();
				} else if (c == 2) {
					a.setStatus(2);
					if (empServ.update(a)) {
						System.out.println("Account " + a.getAccId() + " is now Denied\n"
								+ "It is now available to be deleted by an admin");
					} else {
						System.out.println("A problem occured while trying to Open Account " + a.getAccId());
					}
				} else {
					return;
				}
			}
				
			
		} else {
			System.out.println("There are no Pending accounts at this time...");
		}
		
	}

	@Override
	protected void displayOptions() {
		super.displayOptions();
		System.out.println(count++ + " :\tView all Users");
		System.out.println(count++ + " :\tView by User");
		System.out.println(count++ + " :\tView all Accounts");
		System.out.println(count++ + " :\tView by Account");
		System.out.println(count++ + " :\tApprove or Deny Accounts");
	}
	
	@Override
	protected void displayHeader() {
		System.out.println(cur.getFirstName() + "'s EMPLOYEE VIEW");
	}
	
	
}
