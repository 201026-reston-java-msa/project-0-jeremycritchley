package com.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.models.Account;
import com.models.User;
import com.services.AdminService;

public class AdminServiceTests {

	private static final AdminService adServ = new AdminService();
	
	@Test
	public void testRemoveUserFail() {
		User u = new User(0, "fake", "fakerson", "fake", "fakefake", "fake@fake", "fake");
		assertFalse(adServ.removeUser(u));
	}
	
	@Test
	public void testRemoveUserSuccess() {
		User u = new User(3, "remove", "remove", "remove", "remove", "remove", "remove");
		assertTrue(adServ.removeUser(u));
	}
	
	@Test
	public void testRemoveAccountFail() {
		Account a = new Account(0, 0, 0.0, 0);
		assertFalse(adServ.removeAccount(a));
	}
	
	@Test
	public void testRemoveAccountSuccess() {
		Account a = new Account(3, 0, 0.0, 0);
		assertTrue(adServ.removeAccount(a));
	}
	
	
}
