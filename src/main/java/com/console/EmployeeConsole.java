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
		// TODO Auto-generated method stub
		
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
