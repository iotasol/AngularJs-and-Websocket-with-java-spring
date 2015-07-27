package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demo.util.BeanLocatorService;

@Configuration
@EnableJpaRepositories(basePackages = "com.demo.repository")
@ComponentScan(basePackages = { "com.demo.service", "com.demo.model" })
@EnableTransactionManagement
public class BeanConfig {

	@Bean
	public BeanLocatorService beanLocatorService() {
		return new BeanLocatorService();
	}
}
