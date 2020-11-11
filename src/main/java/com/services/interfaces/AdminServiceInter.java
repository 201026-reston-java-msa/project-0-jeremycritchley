package com.services.interfaces;

import com.models.Account;
import com.models.User;

public interface AdminServiceInter {
	
	/*
	 * remove Account by 
	 * @Param Account acc
	 */
	boolean removeAccount(Account acc);
	
	/*
	 * remove User by 
	 * @Param User usr
	 */
	boolean removeUser(User usr);
}
