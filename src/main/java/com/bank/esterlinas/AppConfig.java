package com.bank.esterlinas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.models.PhoneInfo;
import com.models.User;
import com.services.bank.UserService;

@Configuration
public class AppConfig {
	
	
	@Bean
	public User user() {
		return new User();
	}

	
	@Bean
	public UserService userService() {
		return new UserService();
	}
	
	@Bean
	public PhoneInfo phoneInfo() {
		return new PhoneInfo();
	}
	
}
