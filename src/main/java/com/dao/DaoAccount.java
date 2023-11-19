package com.dao;

import org.springframework.stereotype.Repository;

import com.bank.interfaces.Money;

@Repository
public class DaoAccount implements Money{

	@Override
	public int withdraw() {
		// TODO Auto-generated method stub
		return 07;
	}

	@Override
	public int transfer() {
		// TODO Auto-generated method stub
		return 880;
	}

	@Override
	public int checkBalance(int numero) {
		// TODO Auto-generated method stub
		return numero;
	}

}
