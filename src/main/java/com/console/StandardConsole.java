package com.console;

import com.models.Account;
import com.models.User;

public class StandardConsole extends Console {
	
	public StandardConsole(User u) {
		this.cur = u;
	}

	@Override
	protected void processInput(int in) {

		if (in == 1) {
			displayPersonalAccountOptions();
		} else if (in == 2) {
			System.out.println(cur.toString());
		} else if (in == 0){
			logout();
		} else {
			System.out.println("Invalid Input");
		}
		
	}

	@Override
	protected void applyForAccount(int userId) {
		Account acc = new Account(0, cur.getUserId(), 0, 0);
		acc = accServ.create(acc);
		System.out.println("An Account has been opened for you\n."
				+ "It's default status is PENDING");
		
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
