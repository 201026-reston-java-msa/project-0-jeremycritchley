package com.console;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.Account;
import com.models.User;
import com.services.AccountService;
import com.utils.ScannerUtil;

public abstract class Console {
	
	protected User cur;
	private boolean run;
	protected Scanner input;
	protected int count;
	protected AccountService accServ;
	protected static Logger log = Logger.getLogger(Console.class);
	
	/*
	 * Infinite loop for the logged in user
	 * 	- display's user's respective header
	 * 	- display's user's respective options
	 * 	- prompts for and processes input
	 */
	public void run() {
		accServ = new AccountService();
		input = ScannerUtil.getScanner();
		run = true;
		
		
		while (run) {
			
			count = 1;
			System.out.println();
			displayHeader();
			
			displayOptions();
			
			displayLogout();
			System.out.println();
			
			try {
				int in = Integer.parseInt(input.nextLine());
				
				processInput(in);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Invalid Input....");
			}
		
		}
		
	}
	
	/*
	 * Own method so it can always be last option
	 */
	protected void displayLogout() {
		System.out.println("0 : Logout");
	}
	
	/*
	 * verification before logging out,
	 * ends user specific loop in run()
	 */
	protected void logout() {
		
		String c = "";
		do {
			System.out.println("Are you sure you want to logout? [y/n]");
			c = input.nextLine();
			
		} while (!c.equalsIgnoreCase("y") && !c.equalsIgnoreCase("n"));
		
		if (c.equalsIgnoreCase("y")) {
			run = false;
			log.info("LOGGING OUT USER " + cur.getUserId());
		} 
		
	}
	
	/*
	 * Specific to each role
	 * 
	 * To be overridden in concrete subclasses
	 */
	protected abstract void processInput(int in);

	
	/*
	 * Available to every role
	 * Displays options to:
	 * 	- Withdraw 
	 * 	- Deposit
	 * 	- Transfer
	 * from every OPEN user specific account
	 * 
	 * prompts to create an account if none exist
	 */
	protected void displayPersonalAccountOptions() {
		
		accServ = new AccountService();
		List<Account> accs = accServ.getAccountsByUser(cur.getUserId());
		
		
		if (accs == null) {
			// if no accounts exist,
			// prompt to create one
			System.out.println("Sorry... but it doesn't seem like you have any accounts");
			String c = "";
			do {
				System.out.println("Would you Like to apply for one? [y/n]");
				c = input.nextLine();
			} while (!c.equalsIgnoreCase("y") && c.equalsIgnoreCase("n"));
			
			if (c.equalsIgnoreCase("y")) {
				applyForAccount(cur.getUserId());
			}
		} else { 
			// account(s) exist
			// display all user's accounts' information
			// display account options under every open account
			int c = -1;
			
			do {
				System.out.println("Enter the number of the Account specific action that you wish to perform");
				int i = 1;
				for (Account acc : accs) {
					System.out.println(acc.toString());
					if (acc.getStatus() == 1) {
						System.out.println(i++ + " :\tWithdraw\n" + i++ + " :\tDeposit\n" + i++ + " :\tTransfer");
					}
				}
				System.out.println("0 :\tReturn to Home Screen");
				try {
					c = Integer.parseInt(input.nextLine());
				} catch (Exception e) {
					System.out.println("Try Again...");
				}
			} while (c > accs.size() * 3 || c < 0);
			
			// process selected account option
			processAccountOption(accs.get((c-1)/3), c);
		}
	}

	protected void processAccountOption(Account account, int c) {
		if (c == 0) {
			
		} else if (c%3 == 1) {
			promptWithdraw(account);
		} else if (c%3 == 2) {
			promptDeposit(account);
		} else if (c%3 == 0){
			promptTransfer(account);
		}
		
	}

	/*
	 * @Param acc - source Account to transfer from
	 */
	protected void promptTransfer(Account acc) {

		System.out.println("Please Enter the target Account ID");
		
		try {
			int id = Integer.parseInt(input.nextLine());
			Account destAcc = accServ.getAccountById(id);
			
			if (destAcc == null) {	// destination account ID does not exist
				System.out.println("Sorry, but that Account does not exist.\n"
						+ "Rerouting you to home [Console]");
				return;
			}
			
			double amount = 0;
			while (amount <= 0) {
				System.out.println("Please enter the amount to Transfer (greater than 0)");
				amount = Double.parseDouble(input.nextLine());
			}
			
			if (accServ.transfer(acc, destAcc, amount)) { // do transfer, check result
				System.out.println("Transfer Successful");
			} else {
				System.out.println("Unsuccessful transfer of funds from Account " + destAcc.getAccId() +
						" to Account " + acc.getAccId());
			}
		} catch(Exception e) {
			System.out.println("Invalid Input...");
			System.out.println("Rerouting to home [Console]");
		}
		
	}

	/*
	 * @Param acc - Account to deposit into
	 */
	protected void promptDeposit(Account acc) {
		System.out.println("Please Enter the amount to deposit");
		
		try {
			double amount = 0;
			while (amount <= 0) {	// check for negative values on input
				System.out.println("Please enter the amount to Deposit (greater than 0)");
				amount = Double.parseDouble(input.nextLine());
			}
			
			if (accServ.deposit(acc, amount)) {	// do deposit, check result
				System.out.println("Successful Deposit");
			} else {
				System.out.println("Unsuccessful Deposit to Account " + acc.getAccId());
			}
			
		} catch (Exception e) {
			System.out.println("Invalid Input...");
			System.out.println("Rerouting to home [Console]");
		}
	}

	/*
	 * @Param acc - Account to withdraw from
	 */
	protected void promptWithdraw(Account acc) {
		
		try {
			double amount = 0;
			while (amount <= 0) {
				System.out.println("Please enter the amount to Withdraw (greater than 0)");
				amount = Double.parseDouble(input.nextLine());
			}
			if (accServ.withdraw(acc, amount)) {
				System.out.println("Successful Withdraw");
			} else {
				System.out.println("Unsuccessful Withdraw from Account " + acc.getAccId());
			}
		} catch (Exception e) {
			System.out.println("Invalid Input...");
			System.out.println("Rerouting to home [Console]");
		}
	}

	/*
	 * Displays prompt to apply for an account
	 * 	- if yes, create account for current user
	 * 
	 * To be overridden in concrete subclasses
	 */
	protected abstract void applyForAccount(int userId);

	/*
	 * Displays banking options specific to the current user's role
	 * 
	 * To be overridden in concrete subclasses
	 */
	protected abstract void displayOptions();

	/*
	 * Displays header specific to current user and their role
	 * 
	 * To be overridden in concrete subclasses
	 */
	protected abstract void displayHeader();

	protected abstract void displayUserInfo();
	
	protected abstract void promptUpdateUser();
	
}
