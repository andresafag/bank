package com.bank.interfaces;

import java.math.BigInteger;

public interface UserAuthentication {
	
	public int Authentication(String user, String password);
	
	public boolean checkUser(String usrName, String psswd);

	public int registerUser(String name, String lastName, String userName, String password, BigInteger phoneNumber);
	
	
}
