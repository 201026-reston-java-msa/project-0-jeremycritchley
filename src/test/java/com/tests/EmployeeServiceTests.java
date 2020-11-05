package com.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.models.Account;
import com.services.EmployeeService;

public class EmployeeServiceTests {
	
	private static EmployeeService empServ = new EmployeeService();
	
	@Test
	public void testViewAllAccounts() {
		assertNotNull(empServ.viewAllAccounts());
	}
	
	@Test
	public void testViewAllCustomers() {
		assertNotNull(empServ.viewAllCustomers());
	}
	
	@Test
	public void testViewByUser() {
		assertNotNull(empServ.viewByUser(1));
	}
	
	@Test
	public void testViewByUserFail() {
		assertNull(empServ.viewByUser(0));
	}
	
	@Test
	public void testViewOpenAccounts() {
		
	}
	
	@Test
	public void testUpdateFail() {
		Account a = new Account(0,0,0.0,0);
		assertFalse(empServ.update(a));
	}
	
	@Test
	public void testUpdateSuccess() {
		Account a = new Account(1,1,100.0,1);
		assertTrue(empServ.update(a));
	}
	
}
