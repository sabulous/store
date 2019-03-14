package com.sabulous.store.config;

import com.sabulous.store.Store;
import com.sabulous.store.model.*;
import com.sabulous.store.repository.*;
import com.sabulous.store.service.ProductService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	
@Configuration
@EnableWebMvc
@ComponentScan("com.sabulous.config")
/**
 * Java configuration file that is used for Spring MVC and Thymeleaf configurations
 * // Inserts initial test data into database.
 */
public class JavaConfiguration implements WebMvcConfigurer,  ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductService productService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public CommandLineRunner demo() {
		productService.addProduct(new Product("TV", "television SMARTTV."));

		Logger log = Store.getLogger();
		return (args) -> {
			// save a couple of Products to db
			repository.save(new Product("Table", "Wooden table, white."));
			repository.save(new Product("Bin", "Metal bin with basket."));
			repository.save(new Product("Pan", "Ceramic pan for frying."));
			repository.save(new Product("Towel Hanger", "Adhesive hanger, 5kg limit."));
		};
	}
}
