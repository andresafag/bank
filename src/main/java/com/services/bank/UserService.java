package com.services.bank;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;
import com.dao.DaoUser;

@Service
public class UserService {
	
	
	public boolean verifyUser(String usrName, String psswd) {
		DaoUser daousr = new DaoUser();
		return daousr.checkUser(usrName, psswd);
	}
	

	public String sendConfirmationSMS() {

			 Twilio.init("ACce0a292656b36d6ab1bba58a271a1c21", "3a793270128388020a678faf110349ad");
		     Message message = Message.creator(
		             new com.twilio.type.PhoneNumber("+573002948499"),
		             new com.twilio.type.PhoneNumber("+14156920545"),
		             "Where's Wallace?")
		         .create();

		     System.out.println(message.getSid());
		     return message.getSid();
	

	}
	 
	
}
