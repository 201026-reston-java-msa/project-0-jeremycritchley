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
				System.out.println(u.toString());
			}
			
		} else if (n == 4) {
			
			System.out.println("Please enter the UserID of the user you wish to view");
			int userId = input.nextInt();
			User u = empServ.viewByUser(userId);
			if (u == null) {
				System.out.println("Invalid UserID");
			} else {
				u.toString();
			}
			
		} else if (n == 5) {
			
			List<Account> accs = empServ.viewAllAccounts();
			for (Account a: accs) {
				System.out.println(a.toString());
			}
			
		} else if (n == 6) {
			
			System.out.println("Please enter the AccountID of the account you wish to view");
			int accId = input.nextInt();
			Account a = accServ.getAccountById(accId);
			if (a == null) {
				System.out.println("Invalid AccountID");
			} else {
				a.toString();
			}
			
		} else if (n == 7) {
			
			approveOrDeny();
			
		} else {
			super.processInput(n);
		}
	}
	
	private void approveOrDeny() {
		
		
		List<Account> accs = accServ.getByStatus(0);
		
		if (accs != null) {
			System.out.println("Listing Pending Accounts to Approve or Deny\nenter 0 to exit");
			
			for (Account a : accs) {
				
				int c = -1;
				do {
					System.out.println("Enter:\n\t1 to Approve\n\t2 to Deny\n\t0 to exit");
					a.toString();
					try {
						c = input.nextInt();
					} catch (Exception e) {
						System.out.println("Please enter a Number, Employee");
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
					a.setStatus(3);
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
	}
	
	@Override
	protected void displayHeader() {
		System.out.println(cur.getFirstName() + "'s EMPLOYEE VIEW");
	}
	
	
}
