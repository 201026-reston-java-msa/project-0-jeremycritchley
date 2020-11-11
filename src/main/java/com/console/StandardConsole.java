package com.console;

import com.models.Account;
import com.models.User;
import com.services.LoginService;

public class StandardConsole extends Console {
	
	public StandardConsole(User u) {
		this.cur = u;
	}

	@Override
	protected void processInput(int in) {

		if (in == 1) {
			displayPersonalAccountOptions();
		} else if (in == 2) {
			displayUserInfo();
		} else if (in == 0){
			logout();
		} else {
			System.out.println("Invalid Input");
		}
		
	}

	@Override
	protected void displayUserInfo() {
		System.out.println(cur.toString());
		String c = "";
		while (!c.equalsIgnoreCase("y") && !c.equalsIgnoreCase("n")) {
			System.out.println("Would you like to update your personal information? [y/n]");
			c = input.nextLine();
		}
		
		if (c.equalsIgnoreCase("y")) {
			promptUpdateUser();
		}
				
		
	}
	
	@Override
	protected void promptUpdateUser() {
		LoginService ls = new LoginService();
		boolean commit = false;
		while (!commit) {
			String c = "";
			int n = -1;
			while (n < 0 || n > 5) {
				System.out.println("Update:\n"
						+ "1 :\tFirst Name\n"
						+ "2 :\tLast Name\n"
						+ "3 :\tUsername\n"
						+ "4 :\tPassword\n"
						+ "5 :\tEmail\n"
						+ "0 :\tCommit Update");
				c = input.nextLine();
				
				try {
					n = Integer.parseInt(c);
				} catch (Exception e) {
					
				}
			}
			
			if (n != 0) 
				System.out.print("Please enter new ");
			
			switch (n) {
				case 1:
					System.out.println("First Name");
					cur.setFirstName(input.nextLine());
					break;
				case 2:
					System.out.println("Last Name");
					cur.setLastName(input.nextLine());
					break;
				case 3:
					System.out.println("Username");
					String temp = input.nextLine();
					if (ls.usernameExists(temp) && !temp.equals(cur.getUsername())) {
						System.out.println("Username already exists");
					}
					cur.setUsername(temp);
					break;
				case 4: 
					System.out.println("Password");
					cur.setPassword(input.nextLine());
					break;
				case 5:
					System.out.println("Email");
					String email = input.nextLine();
					if (ls.emailExists(email) && !email.equals(cur.getEmail())) {
						System.out.println("Email already exists");
					}
					cur.setEmail(email);
					break;
				case 0:
					commit = true;
					break;
			}
		
		}
		
		if (ls.update(cur)) {
			System.out.println("Update Successful");
		} else {
			System.out.println("Update Failed");
		}
		System.out.println();
		
	}

	@Override
	protected void applyForAccount(int userId) {
		
		Account acc = new Account(0, cur.getUserId(), 0, 0);
		acc = accServ.create(acc);
		
		if (acc != null) {
			log.info("ACCOUNT " + acc.getAccId() + " CREATED");
			System.out.println("An Account has been opened for you.\n"
					+ "It's default status is PENDING");
			
		} else {
			log.warn("ACCOUNT COULD NOT BE CREATED");
			System.out.println("Sorry, but we could not create account at this time..");
			
		}
		
	}

	@Override
	protected void displayOptions() {
		
		System.out.println(count++ + " :\tPersonal Account Options");
		System.out.println(count++ + " :\tDisplay Personal Information");
		
	}

	@Override
	protected void displayHeader() {
		
		System.out.println(cur.getFirstName() + "'s Console Banking App");
		
	}
	
	

}
