package com.demo.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.demo.annotation.Production;

@Production
@Configuration
@PropertySource("classpath:config/production/database.properties")
public class ProductionDataConfig {

	@Value("classpath:sql/seed-data.sql")
	private Resource seedScript;

	@Value("classpath:sql/test-data.sql")
	private Resource testDataScript;

	@Value("classpath:sql/drop-data.sql")
	private Resource dropDataScript;

	@Value("${database.driver}")
	private String databaseDriver;

	@Value("${database.url}")
	private String databaseUrl;

	@Value("${database.username}")
	private String databaseUsername;

	@Value("${database.password}")
	private String databasePassword;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource basicDatasource = new DriverManagerDataSource(
				this.databaseUrl, this.databaseUsername, this.databasePassword);
		basicDatasource.setDriverClassName(this.databaseDriver);
		return basicDatasource;
	}

	@Autowired
	@Bean
	public DataSourceInitializer dataSourceInitializer(
			final DataSource dataSource) {

		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		// populator.addScript(seedScript);
		return populator;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			Environment env) throws Exception {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.FALSE);
		vendorAdapter.setShowSql(Boolean.TRUE);
		vendorAdapter.setDatabase(Database.MYSQL);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("default");
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.demo.model");
		factory.setDataSource(dataSource());
		factory.setJpaProperties(jpaProperties());
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());

		return factory;
	}

	Properties jpaProperties() {
		Properties props = new Properties();
		props.put("hibernate.query.substitutions", "true 1, false 0");
		props.put("hibernate.hbm2ddl.auto", this.hibernateHbm2ddlAuto);
		props.put("hibernate.show_sql", this.hibernateShowSql);
		props.put("hibernate.format_sql", this.hibernateFormatSql);
		return props;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
