package com.console;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.models.User;

public class Driver {
	
	private static Logger log = Logger.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		/*
		 *  Infinite loop to keep application running
		 *  	- gets user
		 *  	- instantiates user's respective console based on role
		 */
		
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
		if (cur.getRole().toLowerCase().equals("standard")) {
			console = new StandardConsole(cur);
		} else if (cur.getRole().toLowerCase().equals("employee")) {
			console = new EmployeeConsole(cur);
		} else if (cur.getRole().toLowerCase().equals("admin")) {
			console = new AdminConsole(cur);
		}
		
		// go to console
		console.run();
		
	}

	public static User getCurUser() {
		
		LoginConsole ls = new LoginConsole();
		User u = ls.init();
		return u;
	}

}
