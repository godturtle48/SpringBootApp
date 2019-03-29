package com.misa.report.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.misa.report.model.GeneralLedger;

public class ConfigDatabase {

	
	public static SessionFactory sessionFactory(String nameDB,String traceWithActiveSpanOnly) {
		Configuration configuration=new Configuration();
		configuration.addAnnotatedClass(GeneralLedger.class);
		configuration.setProperty("hibernate.connection.driver_class",
			      "org.mariadb.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url",
			      "jdbc:mariadb://db_container:3306/"+nameDB+"?createDatabaseIfNotExist=" + traceWithActiveSpanOnly);
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "misasme");
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
		SessionFactory  sessionFactory= configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
}
