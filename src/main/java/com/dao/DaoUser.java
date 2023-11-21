package com.dao;

import java.math.BigInteger;
import java.util.Random;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.bank.interfaces.UserAuthentication;
import com.models.Account;
import com.models.User;

import org.hibernate.Transaction;


@Repository
public class DaoUser implements UserAuthentication {
	private static SessionFactory factory;
	
	
	@Override
	public int Authentication(String user, String password) {
		  int res = 10;
		      
		try {
		   factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		   Session session = factory.openSession();
		   Transaction tx = session.beginTransaction();
		    User usr = new User();
		    usr.setName(user);
		    usr.setPassword(password);
		    res = (int) session.save(usr);
		    tx.commit();
		} catch (Throwable ex) { 
		   System.err.println("Failed to create sessionFactory object." + ex);
		   throw new ExceptionInInitializerError(ex); 
		}
	
		return res;
	
}


	@Override
	public boolean checkUser(String usrName, String psswd) {
		factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		Session session = factory.openSession();

		
		//HQL example - Get All Employees
		Transaction tx = session.beginTransaction();
		@SuppressWarnings("deprecation")
		Query query = session.createQuery("FROM User S WHERE S.userName = '"+ usrName +"' AND S.password = '"+ psswd +"' ");
		return query.list().isEmpty();
	}

	
	

	@Override
	public int registerUser(String name, String lastName, String userName, String password) {
		int resu= 0;
		int resi =  0;
		BigInteger max = new BigInteger("999999999999999999");
		BigInteger min = new BigInteger("100000000000000000");
		BigInteger bigInteger = max.subtract(min);
	    Random randNum = new Random();
	    int len = max.bitLength();
	    BigInteger res = new BigInteger(len, randNum);
	    if (res.compareTo(min) < 0) {
	         res = res.add(min);
	         }
	    if (res.compareTo(bigInteger) >= 0) {
	         res = res.mod(bigInteger).add(min);
	         System.out.println("The random BigInteger = "+res);
	   }
	    
	    
	    
	    User usr = new User();
	    Account accnt = new Account();
	    accnt.setAccountNumber(res);
	    accnt.setBalance(0);
	    
	    usr.setName(name);
	    usr.setLastName(lastName);
	    usr.setUserName(userName);
	    usr.setPassword(password);
	    accnt.setUser(usr);
	 
	    
	    try {
	    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	    	Session session = factory.openSession();
		    Transaction tx = session.beginTransaction();
		    resi = (int) session.save(usr);
		    resu = (int) session.save(accnt);
		    System.out.println("resultado de resi" + resi + " resultado de resu " + resu);
		    tx.commit();
		    session.close();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	 
	    
		return resu;
	}
}











