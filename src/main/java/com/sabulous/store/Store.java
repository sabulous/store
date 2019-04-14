package com.sabulous.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication (scanBasePackages = {"com.sabulous"})
public class Store {

	private static final Logger log = LoggerFactory.getLogger(Store.class);
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Store.class, args);

		System.out.println("Main method invoked.");
	}

	public static Logger getLogger() {
		return log;
	}
}

