package com.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.models.Account;
import com.models.User;
import com.services.AccountService;
import com.services.AdminService;
import com.services.EmployeeService;

public class Tests {
	@InjectMocks
	private static final AccountService accServ = new AccountService();
	@InjectMocks
	private static final AdminService adServ = new AdminService();
	@InjectMocks
	private static EmployeeService empServ = new EmployeeService();
	@Mock
	private AccountDAO accd;
	@Mock
	private UserDAO userd;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testWithdraw() {
		Account acc = new Account();
		acc.setAccId(2);
		acc.setBalance(10000);
		accServ.withdraw(acc, 100);
		verify(accd, times(1)).update(acc);
	}
	
	@Test
	public void testWithdrawFail() {
		Account acc = new Account();
		acc.setAccId(2);
		acc.setBalance(100);
		accServ.withdraw(acc, 10000);
		verify(accd, times(0)).update(acc);
	}
/*	
	@Test
	public void testDeposit() {
		assertTrue(accServ.deposit(acc, 100));
	}
	
	@Test
	public void testDepositFail() {
		Account a = acc;
		assertFalse(accServ.deposit(a, -100));
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
	*/
	
}
