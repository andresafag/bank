package com.bank.interfaces;


public interface Money {
	
	public int transfer(String transferTo, String transferFrom, String amount);
	
	public int withdraw();
		
	public Long checkBalance(String numero);
}
