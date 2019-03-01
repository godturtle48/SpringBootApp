package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.example.demo.configmessagequeue.ConfigMessageQueue;
import com.example.demo.rabbidmq.CommandMessageQueue;
import com.example.demo.rabbidmq.CreateMessageQueue;
import com.rabbitmq.client.Command;
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class PaymentReceiptCqrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentReceiptCqrsApplication.class, args);
		ConfigMessageQueue.init();
		CreateMessageQueue.init();
		CommandMessageQueue.init();
		
	}

	
	@Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
 
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
 
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
 
        return mongoTemplate;
 
    }
	

	
	
}