package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import com.bank.esterlinas.AppConfig;
import com.bank.interfaces.Money;
import com.models.PhoneInfo;
import com.models.User;

@Repository
public class DaoAccount implements Money{

	@Override
	public int transfer(String transferTo, String transferFrom, String amount) {
		int res = 0;
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		PhoneInfo phoneFrom = (PhoneInfo) appconfig.getBean(PhoneInfo.class);
		PhoneInfo phoneTo = (PhoneInfo) appconfig.getBean(PhoneInfo.class);
		long stringToNumbersTransferFrom = Long.parseLong(transferFrom);
		long stringToNumbersTransferTo = Long.parseLong(transferTo);
		long stringToNumbersAmount = Long.parseLong(amount);
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
	   
			   
		@SuppressWarnings("deprecation")
		Query queryFrom = session.createQuery("FROM PhoneInfo P INNER JOIN P.user c ON c.id=P.id WHERE P.phoneNumber=:phonenumericValue");
		queryFrom.setParameter("phonenumericValue", stringToNumbersTransferFrom);
		
		@SuppressWarnings("deprecation")
		Query queryTo = session.createQuery("FROM PhoneInfo P INNER JOIN P.user c ON c.id=P.id WHERE P.phoneNumber=:phonenumericValue");
		queryTo.setParameter("phonenumericValue", stringToNumbersTransferTo);
		
		phoneFrom = (PhoneInfo) queryFrom.uniqueResult();
		phoneTo = (PhoneInfo) queryTo.uniqueResult();
	
		if (phoneFrom.getBalance() >= stringToNumbersAmount && !queryTo.list().isEmpty()) {
		Long balanceForBeneficiary = phoneTo.getBalance() + stringToNumbersAmount;
		Long balanceForWhoTransfers = phoneFrom.getBalance() - stringToNumbersAmount;
			
		@SuppressWarnings("deprecation")
		Query TransferMoneyTo = session.createQuery("UPDATE PhoneInfo set balance= :balance where id= :id");
		TransferMoneyTo.setParameter("balance", balanceForBeneficiary);
		TransferMoneyTo.setParameter("id", phoneTo.getId());
		res += TransferMoneyTo.executeUpdate();
			
		@SuppressWarnings("deprecation")
		Query TransferMoneyFrom = session.createQuery("UPDATE PhoneInfo set balance= :balance where id= :id");
		TransferMoneyFrom.setParameter("balance", balanceForWhoTransfers);
		TransferMoneyFrom.setParameter("id", phoneFrom.getId());
		res += TransferMoneyFrom.executeUpdate();
		System.out.println(res);
			
		} else if (phoneFrom.getBalance() >= stringToNumbersAmount && queryTo.list().isEmpty()){
			res = -1;
		} else {
		res = 0;
		}
					
			
	    tx.commit();
   	    session.close();
   	    ((ConfigurableApplicationContext)appconfig).close();
		return res;
	}
	
	
	
	
	@Override
	public int withdraw() {
		// TODO Auto-generated method stub
		return 07;
	}
	

	@Override
	public int checkBalance(int numero) {
		// TODO Auto-generated method stub
		return numero;
	}

}
