package com.akash.spring_junit_test_app.user;

import com.akash.spring_junit_test_app.user.enities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	@Qualifier("userBean")
	public User getUserBean(){
		return new User();
	}

	@Bean
	public RestTemplate getRestTemplateObject(){
		return new RestTemplate();
	}

}
