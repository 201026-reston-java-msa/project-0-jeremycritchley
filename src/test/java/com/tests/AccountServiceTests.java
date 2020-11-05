package com.tests;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.models.Account;
import com.services.AccountService;

public class AccountServiceTests {
	
	private static final AccountService accServ = new AccountService();
	private Account acc = new Account(1, 1, 0.0, 1);;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void testWithdraw() {
		assertTrue(accServ.withdraw(acc, 100));
	}
	
	@Test
	public void testWithdrawFail() {
		assertFalse(accServ.withdraw(acc, 1000000));
	}
	
	@Test
	public void testDeposit() {
		assertTrue(accServ.deposit(acc, 100));
	}
	
	@Test
	public void testTransferSuccess() {
		Account a = acc;
		a.setUserId(2);
		a.setAccId(2);
		assertTrue(accServ.transfer(acc, a, 100));
	}
	
	@Test
	public void testTransferFail() {
		Account a = acc;
		a.setUserId(2);
		a.setAccId(2);
		assertFalse(accServ.transfer(acc, a, 1000000));
	}
	
	
}
