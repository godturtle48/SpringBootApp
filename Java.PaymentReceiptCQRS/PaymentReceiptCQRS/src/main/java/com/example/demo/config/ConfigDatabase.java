package com.example.demo.config;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

import com.example.demo.command.model.InvoiceDetailCommand;
import com.example.demo.command.model.PaymentReceiptCommand;
import com.example.demo.command.model.RefTypeCommand;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class ConfigDatabase {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getInstance() {
		return sessionFactory;		
	}
	
	public static SessionFactory  createSessionFactory(boolean traceWithActiveSpanOnly,String nameDB,String auto) {
		  Configuration configuration = new Configuration();
		  configuration.addAnnotatedClass(PaymentReceiptCommand.class);
		  configuration.addAnnotatedClass(RefTypeCommand.class);
		  configuration.addAnnotatedClass(InvoiceDetailCommand.class);
		  configuration.setProperty("hibernate.connection.driver_class",
			      "org.mariadb.jdbc.Driver");
//			  configuration.setProperty("hibernate.connection.url",
//			      "jdbc:mariadb://db_container:3306/"+nameDB+"?createDatabaseIfNotExist=" + traceWithActiveSpanOnly);
		  	  configuration.setProperty("hibernate.connection.url",
			      "jdbc:mariadb://localhost:3306/"+nameDB+"?createDatabaseIfNotExist=" + traceWithActiveSpanOnly);
			  configuration.setProperty("hibernate.connection.username", "root");
			  configuration.setProperty("hibernate.connection.password", "misasme");
			  configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			  configuration.setProperty("hibernate.hbm2ddl.auto","update");
			  configuration.setProperty("hibernate.show_sql", "true");
			  configuration.setProperty("hibernate.connection.pool_size", "100");
			  configuration.setProperty("hibernate.connection.characterEncoding", "utf8");
			  configuration.setProperty("hibernate.connection.useUnicode", "true");
			  configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");
			  configuration.setProperty("hibernate.search.default.directory_provider", "filesystem");
			  configuration.setProperty("hibernate.search.default.indexBase", "/data/index/default");
		  StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		      .applySettings(configuration.getProperties());
		  sessionFactory= configuration.buildSessionFactory(builder.build());
		  return sessionFactory;
	}
	
//	public void indexBooks() throws Exception {
//	    try {
//	        Session session = sessionFactory.getCurrentSession();
//	        FullTextSession fullTextSession = Search.getFullTextSession(session);
//	        fullTextSession.createIndexer().startAndWait();
//	    } catch(Exception e) {
//	        throw e;
//	    }
//	}
}
