package com.mercadolibre.mutant.mutantdna.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.context.annotation.Bean;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.Arrays;


/**
 * Clase donde se encuantra las configuraciones de los datasource.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
public class DatasourceConfiguration {

	@Autowired
	private Environment environment;

	/**
	 * Metodo encargado de inicializar la conexcion a la BD de mongo
	 * 
	 * @author william corredor
	 * @date 5/11/2021
	 * @since 1.0
	 */
	
	@Primary
	@Bean(name = "companyMongoTemplate")
	public MongoTemplate primaryMongoTemplate() {
		return new MongoTemplate(factory(), environment.getProperty("mongodb.configurationdb"));
	}

	/**
	 * Metodo bean  encargado de conectarce a la BD de mongo.
	 * 
	 * @author william corredor
	 * @date 5/11/2021
	 * @since 1.0
	 */
	
	@Bean
	@Primary
	public MongoClient factory() {
		MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder().applyToClusterSettings(
				builder -> builder.hosts(Arrays.asList(new ServerAddress(environment.getProperty("mongodb.host"),
						Integer.parseInt(environment.getProperty("mongodb.port"))))))
				.build());
		return mongoClient;
	}

}
