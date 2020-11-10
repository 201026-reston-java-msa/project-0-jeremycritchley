package com.console;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.Account;
import com.models.User;
import com.services.AccountService;
import com.services.LoginService;
import com.utils.ScannerUtil;

public abstract class Console {
	
	protected User cur;
	private boolean run;
	protected Scanner input;
	protected int count;
	protected AccountService accServ;
	protected static Logger log = Logger.getLogger(Console.class);
	
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

	protected void displayLogout() {
		System.out.println("0 : Logout");
	}
	
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
	
	protected abstract void processInput(int in);

	
	
	protected void displayPersonalAccountOptions() {
		
		accServ = new AccountService();
		List<Account> accs = accServ.getAccountsByUser(cur.getUserId());
		
		if (accs == null) {
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

	protected void promptTransfer(Account acc) {

		System.out.println("Please Enter the target Account ID");
		
		try {
			int id = Integer.parseInt(input.nextLine());
			Account destAcc = accServ.getAccountById(id);
			if (destAcc == null) {
				System.out.println("Sorry, but that Account does not exist.\n"
						+ "Rerouting you to home [Console]");
			}
			double amount = 0;
			while (amount <= 0) {
				System.out.println("Please enter the amount to Transfer (greater than 0)");
				amount = Double.parseDouble(input.nextLine());
			}
			if (accServ.transfer(acc, destAcc, amount)) {
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

	protected void promptDeposit(Account acc) {
		// TODO Auto-generated method stub
		System.out.println("Please Enter the amount to deposit");
		
		try {
			double amount = 0;
			while (amount <= 0) {
				System.out.println("Please enter the amount to Deposit (greater than 0)");
				amount = Double.parseDouble(input.nextLine());
			}
			if (accServ.deposit(acc, amount)) {
				System.out.println("Successful Deposit");
			} else {
				System.out.println("Unsuccessful Deposit to Account " + acc.getAccId());
			}
			
		} catch (Exception e) {
			System.out.println("Invalid Input...");
			System.out.println("Rerouting to home [Console]");
		}
	}

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

	protected abstract void applyForAccount(int userId);

	protected abstract void displayOptions();

	protected abstract void displayHeader();

	
	
}
