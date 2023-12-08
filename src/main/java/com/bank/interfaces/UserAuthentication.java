package com.bank.interfaces;

import java.util.Map;

public interface UserAuthentication {
	
	public int saveNewUsr(String name, String lastName, String userName, String password, String phoneNumber);
	
	public boolean checkUser(String usrName, String psswd);

	public boolean checkUserPhoneNumber(String name, String lastName, String userName, String password, String phoneNumber);
	
	public Map<String,Object> getUsrInfo(String PhoneNumber);
}
