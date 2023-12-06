package com.bank.interfaces;

import java.util.List;

public interface UserAuthentication {
	
	public int saveNewUsr(String name, String lastName, String userName, String password, String phoneNumber);
	
	public boolean checkUser(String usrName, String psswd);

	public boolean checkUserPhoneNumber(String name, String lastName, String userName, String password, String phoneNumber);
	
	public List<String> getUsrInfo(String PhoneNumber);
}
