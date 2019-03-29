package com.misa.sme.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.misa.sme.config.messagequeue.PaymentMessageQueue;
import com.misa.sme.config.messagequeue.RegisterMessageQueue;
import com.misa.sme.config.messagequeue.RemoteMessageQueue;
import com.misa.sme.config.model.PaymentDatabaseServerInfo;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ConfigApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);	
		RemoteMessageQueue.init();
		PaymentMessageQueue.init();
		RegisterMessageQueue.init();
		ConfigDatabaseForThisService.getInstance();	
		RemoteMessageQueue.genaratePaymentDatabaseServerInfo();
	}

}

