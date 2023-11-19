package com.bank.interfaces;

public interface UserAuthentication {
	
	public int Authentication(String user, String password);
	
	public boolean checkUser(String usrName, String psswd);
}
