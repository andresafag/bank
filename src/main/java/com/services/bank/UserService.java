package com.services.bank;

import org.springframework.stereotype.Service;
import com.dao.DaoUser;

@Service
public class UserService {
	
	
	public boolean verifyUser(String usrName, String psswd) {
		DaoUser daousr = new DaoUser();
		return daousr.checkUser(usrName, psswd);
	}
	 
	
}
