package com.example.addressbookapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressbookapplicationApplication {
	private static final Logger logger =LoggerFactory.getLogger(AddressbookapplicationApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(AddressbookapplicationApplication.class, args);
		logger.info("Application is started...");
	}

}
