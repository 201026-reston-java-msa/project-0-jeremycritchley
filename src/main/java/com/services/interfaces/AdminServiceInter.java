package com.services.interfaces;

import com.models.Account;
import com.models.User;

public interface AdminServiceInter {
	
	boolean removeAccount(Account acc);
	
	boolean removeUser(User usr);
}
