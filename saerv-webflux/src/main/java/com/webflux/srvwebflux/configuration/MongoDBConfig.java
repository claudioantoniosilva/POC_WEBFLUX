package com.webflux.srvwebflux.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.webflux.srvwebflux.dao")
public class MongoDBConfig extends AbstractReactiveMongoConfiguration{

	@Value("${db.port}")
	private String port;
	
	@Value("${db.name}")
	private String dbName;
	
	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}
	
	@Override
	protected String getDatabaseName() {
		return dbName; 
	}
	
	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}
	

		
	
}
