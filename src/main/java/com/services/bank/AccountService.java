package com.services.bank;

import org.springframework.stereotype.Service;
import com.dao.DaoAccount;

@Service
public class AccountService {
	
	
	
	public int transferMoney(String transferTo, String transferFrom, String amount) {
		DaoAccount daomoney = new DaoAccount();
		return daomoney.transfer(transferTo,transferFrom,amount);
	}
}
