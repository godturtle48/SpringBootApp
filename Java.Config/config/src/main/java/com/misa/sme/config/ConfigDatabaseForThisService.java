package com.misa.sme.config;


import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import com.misa.sme.config.model.PaymentDatabaseInfo;
import com.misa.sme.config.model.PaymentDatabaseOfUser;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class ConfigDatabaseForThisService {	
	private static SessionFactory instance;
	
	private ConfigDatabaseForThisService() {
		  Configuration configuration = new Configuration();
		  configuration.addAnnotatedClass(PaymentDatabaseServerInfo.class);
		  configuration.addAnnotatedClass(PaymentDatabaseInfo.class);
		  configuration.addAnnotatedClass(PaymentDatabaseOfUser.class);
		  configuration.setProperty("hibernate.connection.driver_class",
			      "org.mariadb.jdbc.Driver");
			  configuration.setProperty("hibernate.connection.url",
			      "jdbc:mariadb://localhost:3306/config?createDatabaseIfNotExist=true");
			  configuration.setProperty("hibernate.connection.username", "root");
			  configuration.setProperty("hibernate.connection.password", "12345");
			  configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			  configuration.setProperty("hibernate.hbm2ddl.auto","create");
			  configuration.setProperty("hibernate.show_sql", "false");
			  configuration.setProperty("hibernate.connection.pool_size", "100");
			  configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
			  configuration.setProperty("hibernate.connection.useUnicode", "true");
			  configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
			  configuration.setProperty("hibernate.search.default.directory_provider", "filesystem");
			  configuration.setProperty("hibernate.search.default.indexBase", "/data/index/default");
		  StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		      .applySettings(configuration.getProperties());
		  instance= configuration.buildSessionFactory(builder.build());		 
	}
	
	public static SessionFactory getInstance() {
		if (instance==null) new ConfigDatabaseForThisService();
		return instance;
	}
}
