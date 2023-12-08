package com.dao;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import com.bank.esterlinas.AppConfig;
import com.bank.interfaces.UserAuthentication;
import com.models.PhoneInfo;
import com.models.User;
import org.hibernate.Transaction;

@Repository
public class DaoUser implements UserAuthentication {
	
	@Override
	public int saveNewUsr(String name, String lastName, String userName, String password, String phoneNumber) {
		  int result = 0;
		      
		try {
		   SessionFactory factory = new Configuration().configure().buildSessionFactory();
		   Session session = factory.openSession();
		   Transaction tx = session.beginTransaction();
		   long stringToNumbers = Long.parseLong(phoneNumber);
			ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
			User usr = (User) appconfig.getBean(User.class);
			PhoneInfo phone = (PhoneInfo) appconfig.getBean(PhoneInfo.class);
		   

	   	    phone.setBalance(0);
	   	    phone.setPhoneNumber(stringToNumbers);
	   	    usr.setName(name);
	   	    usr.setLastName(lastName);
	   	    usr.setUserName(userName);
	   	    usr.setPassword(password);
	   	    phone.setUser(usr);
	   	    session.save(usr);
	   	    result = (int) session.save(phone);
	   	    tx.commit();
	   	    session.close();
	   	 ((ConfigurableApplicationContext)appconfig).close();
		} catch (Throwable ex) { 
		   System.err.println("Failed to create sessionFactory object." + ex);
		   throw new ExceptionInInitializerError(ex); 
		}
	
		return result;
	
}
	

	// This section verifies the user information and lets him/her in --------------------------------------------------------------------------------------------------
	@Override
	public boolean checkUser(String phoneNumber, String psswd) {
		long stringToNumbers = Long.parseLong(phoneNumber);
//		factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		@SuppressWarnings("deprecation")
		Query query = session.createQuery("FROM PhoneInfo P INNER JOIN P.user c ON c.id=P.id WHERE P.phoneNumber=:phonenumericValue AND c.password=:passwd");
		query.setParameter("phonenumericValue", stringToNumbers);
		query.setParameter("passwd", psswd);
		return query.getResultList().isEmpty();
	}

	

	
	@Override
	public boolean checkUserPhoneNumber(String name, String lastName, String userName, String password, String phoneNumber) {
		boolean result = false;
		long phoneNumberConverted =  Long.parseLong(phoneNumber);
	    try {
	    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	    	Session session = factory.openSession();
		    
		    @SuppressWarnings("deprecation")
			Query query = session.createQuery("FROM PhoneInfo P INNER JOIN P.user c ON c.id=P.id WHERE P.phoneNumber=:phonenumericValue");
			query.setParameter("phonenumericValue", phoneNumberConverted);
		    
			if(query.getResultList().isEmpty() == true) {
		    	result = query.getResultList().isEmpty();
				System.out.println("No existe y puedes proceder");
		    } else {
		    	System.out.println("ya existe el numero de telefono");
		    }
    
		 
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		return result;
	}


	
	@Override
	public Map<String,Object> getUsrInfo(String phoneNumber) {
		ApplicationContext appconfig =  new AnnotationConfigApplicationContext(AppConfig.class);
		PhoneInfo phone = (PhoneInfo) appconfig.getBean(PhoneInfo.class);
		
		long phoneNumberConverted =  Long.parseLong(phoneNumber);
		Map<String, Object> userInfo = new HashMap<String,Object>();
		try {
	    	SessionFactory factory = new Configuration().configure().buildSessionFactory();
	    	Session session = factory.openSession();
		    
		    @SuppressWarnings("deprecation")
			Query query = session.createQuery("FROM PhoneInfo P INNER JOIN P.user c ON c.id=P.id WHERE P.phoneNumber=:phonenumericValue");
			query.setParameter("phonenumericValue", phoneNumberConverted);
		    
			if(query.getResultList().isEmpty() == true) {
				System.out.println("los datos no están");
		    } else {
		    	System.out.println("los datos están " + query.list());
		    	
		    	phone = (PhoneInfo) query.uniqueResult();
		    	userInfo.put("name", phone.getUser().getName());		
		    	userInfo.put("lastname", phone.getUser().getLastName());		
		    	userInfo.put("username", phone.getUser().getUserName());	
		    	userInfo.put("phonenumber", phone.getPhoneNumber());	
		    	userInfo.put("balance", phone.getBalance());		
		    	
		    	((ConfigurableApplicationContext)appconfig).close();
		    }
		 
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		return userInfo;
	}
}











