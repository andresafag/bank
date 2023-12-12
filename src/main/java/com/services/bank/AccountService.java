package com.services.bank;

import org.springframework.stereotype.Service;
import com.dao.DaoAccount;

@Service
public class AccountService {
	
	
	
	public int transferMoney(String transferTo, String transferFrom, String amount) {
		DaoAccount daoMoney = new DaoAccount();
		return daoMoney.transfer(transferTo,transferFrom,amount);
	}
	
	public Long returnBalance(String transferFrom) {
		DaoAccount daomoney = new DaoAccount();
		return daomoney.checkBalance(transferFrom);
	}
	
	public int checkWithdrawalAmount(String amount, String phoneNumber) {
		DaoAccount daoMoney = new DaoAccount();
		return daoMoney.withdraw(amount,phoneNumber);
	}
	
	
}
