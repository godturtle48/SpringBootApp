package com.example.demo.configmessagequeue;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.model.RefTypeCommand;

public class TenantConfig {
	public static SessionFactory  create(boolean traceWithActiveSpanOnly,String host,String port,String keydatabase,
			String username,String password) {
		  Configuration configuration = new Configuration();
		  configuration.addAnnotatedClass(PaymentReceiptCommand.class);
		  configuration.addAnnotatedClass(RefTypeCommand.class);
		  configuration.addAnnotatedClass(InvoiceDetailCommand.class);
		  configuration.setProperty("hibernate.connection.driver_class",
			      "org.mariadb.jdbc.Driver");
			  configuration.setProperty("hibernate.connection.url",
			      "jdbc:mariadb://"+host+":"+port+"/"+keydatabase+"?createDatabaseIfNotExist=" + traceWithActiveSpanOnly);
			  configuration.setProperty("hibernate.connection.username", username);
			  configuration.setProperty("hibernate.connection.password", password);
			  configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			  configuration.setProperty("hibernate.hbm2ddl.auto","update");
			  configuration.setProperty("hibernate.show_sql", "false");
			  configuration.setProperty("hibernate.connection.pool_size", "10");
			  configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
			  configuration.setProperty("hibernate.connection.useUnicode", "true");
			  configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		  StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		      .applySettings(configuration.getProperties());
		  return configuration.buildSessionFactory(builder.build());
	}
}
