package com.console;

import java.util.Scanner;

import com.models.User;
import com.services.LoginService;

public class LoginConsole {
	
	private Scanner input;
	private LoginService ls;
	
	User login() {
		ls = new LoginService();
		
		input = new Scanner(System.in);
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
		
		input = new Scanner(System.in);
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
}
