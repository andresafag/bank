package com.services.bank;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import java.util.Random;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;
import com.dao.DaoUser;

@Service
public class UserService {
	
	
	public boolean verifyUser(String phoneNumber, String psswd) {
		DaoUser daousr = new DaoUser();
		return daousr.checkUser(phoneNumber, psswd);
	}
	

	public String sendConfirmationSMS(String sendTo) {
			 Random rand = new Random() ;
			 String messagingResult = null;
			 int magicNumber = rand.nextInt() & Integer.MAX_VALUE;
			 String magicNumberToString = String.valueOf(magicNumber);
			 Twilio.init("ACce0a292656b36d6ab1bba58a271a1c21", "3a793270128388020a678faf110349ad");
			 
			 try {
			     Message message = Message.creator(
			             new com.twilio.type.PhoneNumber(String.format("+57%s", sendTo)),
			             new com.twilio.type.PhoneNumber("+14156920545"),magicNumberToString)
			         .create();
			     System.out.println(message.getSid());
			     System.out.println("este es el numero " + magicNumberToString);
			     messagingResult = message.getSid();
			     
			} catch (ApiException e) {
				System.out.println("pailas");
				JOptionPane.showMessageDialog(null,"Invalid phone number",
			               "Please a valid phone number", JOptionPane.WARNING_MESSAGE);
			}

			 return messagingResult != null ? magicNumberToString : "0";
	}
	
	public boolean checkPhoneNumber(String name, String lastName, String userName, String password, String phoneNumber) {
		DaoUser daousr = new DaoUser();
		return daousr.checkUserPhoneNumber(name, lastName, userName, password, phoneNumber);
	}
	
	
	
	public int saveUsr(String name, String lastName, String userName, String password, String phoneNumber) {
		DaoUser daousr = new DaoUser();
		return daousr.saveNewUsr(name, lastName, userName, password, phoneNumber);
	}
	 
	
}
