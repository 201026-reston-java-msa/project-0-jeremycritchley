package com.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.models.Account;
import com.models.User;
import com.services.AccountService;
import com.services.AdminService;
import com.services.EmployeeService;

public class Tests {
	/*
	@InjectMocks
	private static final AccountService accMock = new AccountService();
	@InjectMocks
	private static final AdminService adMock = new AdminService();
	@InjectMocks
	private static EmployeeService empMock = new EmployeeService();
	*/
	private AccountDAO accd;
	
	private UserDAO userd;
	
	private Account acc;
	private static final AccountService accServ = new AccountService();
	private static final AdminService adServ = new AdminService();
	private static EmployeeService empServ = new EmployeeService();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		accd = new AccountDAO();
		userd = new UserDAO();
		acc = new Account();
		acc.setAccId(40);
		acc.setUserId(30);
		acc.setStatus(1);
		acc.setBalance(1234567);
		
	}
	
	@Test
	public void testWithdraw() {
		assertTrue(accServ.withdraw(acc, 1));
	}
	
	@Test
	public void testWithdrawFail() {
	
		assertFalse(accServ.withdraw(acc, Double.MAX_VALUE));
	}
	
	@Test
	public void testDeposit() {
		assertTrue(accServ.deposit(acc, 1));
	}
	
	@Test
	public void testDepositFail() {
		Account a = acc;
		assertFalse(accServ.deposit(a, -100));
	}
	
	@Test
	public void testTransferSuccess() {
		Account a = acc;
		a.setAccId(41);
		
		assertTrue(accServ.transfer(acc, a, 1));
	}
	
	@Test
	public void testTransferFail() {
		Account a = acc;
		
		a.setAccId(41);
		assertFalse(accServ.transfer(acc, a, Double.MAX_VALUE));
	}
	

	
	@Test
	public void testRemoveUserFail() {
		
		User u = new User(0, "fake", "fakerson", "fake", "fakefake", "fake@fake", "fake");
		//u = userd.create(u);
		
		assertFalse(adServ.removeUser(u));
	}
	
	@Test
	public void testRemoveUserSuccess() {
		User u = new User(0, "fake", "fakerson", "fake", "fakefake", "fake@fake", "fake");
		u = userd.create(u);
		
		assertTrue(adServ.removeUser(u));
	}
	
	/*@Test
	public void testRemoveAccountFail() {
		Account a = new Account(-1, -1, 0.0, -1);
		assertFalse(adServ.removeAccount(a));
	}*/
	
	@Test
	public void testRemoveAccountSuccess() {
		Account a = new Account(0, 30, 0.0, 0);
		a = accd.create(a);
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
		assertNotNull(empServ.viewByUser(30));
	}
	
	@Test
	public void testViewByUserFail() {
		assertNull(empServ.viewByUser(-1));
	}
	
	@Test
	public void testViewOpenAccounts() {
		
	}
	
	/*@Test
	public void testUpdateFail() {
		Account a = new Account(-1,-1,0.0,-1);
		assertFalse(empServ.update(a));
	}*/
	
	@Test
	public void testUpdateSuccess() {
		acc.setBalance(acc.getBalance()+1);
		assertTrue(empServ.update(acc));
	}

	
}
