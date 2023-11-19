package com.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.bank.interfaces.UserAuthentication;
import com.beans.User;
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
		Query query = session.createQuery("FROM User S WHERE S.userName = '"+ usrName +"' ");
		return query.list().isEmpty();
	}
}











