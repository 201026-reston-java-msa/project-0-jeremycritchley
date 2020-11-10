package com.console;

import java.util.Scanner;

import com.models.User;
import com.services.LoginService;
import com.utils.ScannerUtil;

public class LoginConsole {
	
	private Scanner input;
	private LoginService ls;
	
	User login() {
		ls = new LoginService();
		
		System.out.println("Login to the Console Banking Application");
		System.out.println("Username:");
		String username = input.nextLine();
		System.out.println("Password:");
		String password = input.nextLine();
		
		User cur = ls.login(username, password);
		
		return cur;
	}
	
	User register() {
		ls = new LoginService();
		
		System.out.println("Welcome Newcomer to the Console Banking Application\n"
				+ "Just a few things to get started...\n"
				+ "Please Enter :\n"
				+ "First Name:");
		
		String firstname = input.nextLine();
		
		System.out.println("Last Name:");
		String lastname = input.nextLine();

		System.out.println("Email:");
		String email = input.nextLine();
		while (ls.emailExists(email)) {
			System.out.println("There is already an account with that email\n"
					+ "Please enter another");
			email = input.nextLine();
		}
		
		System.out.println("Username:");
		String username = input.nextLine();
		
	
		while (ls.usernameExists(username)) {
			System.out.println("Sorry but that Username is taken\n"
					+ "Please enter another");
			username = input.nextLine();
		}
		
		System.out.println("Password:");
		String password = input.nextLine();
		
		
		
		User cur = new User(0, username, password, firstname, lastname, email, "standard");
		cur = ls.register(cur);
		
		return cur;
	}

	public User init() {
		
		input = ScannerUtil.getScanner();
		int choice = -1;
		
		while (choice != 1 && choice != 2 && choice != 0) {

			System.out.println("Please enter a:\n" + "\t1 : to login to an existing account\n"
					+ "\t2 : to register a new account\n" + "\t0 : to quit the application\n");

			try {
				choice = Integer.parseInt(input.nextLine());
			} catch (Exception e) {
				System.out.println("That was not even a number...");
			}
		}

		User cur = null;

		if (choice == 1) {
			cur = login();
		} else if (choice == 2) {
			cur = register();
		} else {

			System.out.println("Thank you for using the Console Banking Application\nGoodbye\n\n");
			System.exit(0);
		}

		if (cur == null) {
			System.out.println("Failed to access account");

			String c = "";
			do {
				System.out.println("Would you like to try again? [y/n]");
				c = input.nextLine();

			} while (!c.equalsIgnoreCase("y") && !c.equalsIgnoreCase("n"));

			if (c.equalsIgnoreCase("y")) {
				return null;
			} else {
				System.out.println("Thank you for using the Banking Application\nGoodbye\n\n");
			}

		}
		return cur;
	}
}
