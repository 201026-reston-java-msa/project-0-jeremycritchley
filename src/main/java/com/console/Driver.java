package com.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.models.User;
import com.services.LoginService;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the Console Based Banking Application");
		
		run();
		
	}
	
	public static void run() {
		
		Scanner input = new Scanner(System.in);
		// create new LoginService
		// Login or Register
		
		int choice = 0;
		while (choice != 1 && choice != 2) {
			System.out.println("Please enter a: "
					+ "\t1 : to login to an existing account"
					+ "\t2 : to register a new account");
		
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("That was not even a number...");
			}
		}
		
		LoginService ls = new LoginService();
		User cur = null;
		if (choice == 1) {
			cur = ls.login();
		} else {
			cur = ls.register();
		}
		
		if (cur == null) {
			
		}
		// based on Login credentials
		// create admin, employee, or standard console
		
		if (cur.getRole().equals("standard")) {
			
		} else if (cur.getRole().equals("employee")) {
			
		} else if (cur.getRole().equals("admin")) {
			
		}
	}

}
