package com.bank.esterlinas;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.models.PhoneInfo;
import com.models.User;
import com.services.bank.AccountService;
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
	public AccountService accountService() {
		return new AccountService();
	}
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public PhoneInfo phoneInfo() {
		return new PhoneInfo();
	}
	
}
