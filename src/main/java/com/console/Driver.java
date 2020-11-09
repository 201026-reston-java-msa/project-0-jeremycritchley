package com.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.User;

public class Driver {
	
	private static Logger log = Logger.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		while (true) {
			
			System.out.println("Welcome to the Console Based Banking Application");
			
			User u = getCurUser();
			if (u != null) {
				log.info("LOGGING IN USER " + u.getUserId());
				goToService(u);
			}
		}
		
	}
	
	private static void goToService(User cur) {
		
		// based on Login credentials
		// create admin, employee, or standard console
		Console console = null;
		if (cur.getRole().equals("standard")) {
			console = new StandardConsole(cur);
		} else if (cur.getRole().equals("employee")) {
			console = new EmployeeConsole(cur);
		} else if (cur.getRole().equals("admin")) {
			console = new AdminConsole(cur);
		}
		
		console.run();
		
	}

	public static User getCurUser() {
		
		Scanner input = new Scanner(System.in);
		// create new LoginService
		// Login or Register
		
		int choice = -1;
		while (choice != 1 && choice != 2 && choice != 0) {
			System.out.println("Please enter a:\n"
					+ "\t1 : to login to an existing account\n"
					+ "\t2 : to register a new account\n"
					+ "\t0 : to quit the application\n");
		
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That was not even a number...");
				input.nextLine();
			}
		}
		
		LoginConsole ls = new LoginConsole();
		User cur = null;
		
		if (choice == 1) {
			cur = ls.login();
		} else if (choice == 2){
			cur = ls.register();
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
				input.close();
				return null;
			} else {
				System.out.println("Thank you for using the Banking Application\nGoodbye\n\n");
				System.exit(0);
			}
			
		}
		input.close();
		return cur;
		
		
		
		
	}

}
