package com.mercadolibre.mutant.mutantdna;

import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


import lombok.extern.slf4j.Slf4j;

/**
 * Clase cencargada de escribir en el log el contexto con el que se sube el servicio
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */

@Slf4j
public class StarterWebApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	
	/**
	 * Metodo encargado de escribir en el log el contexto de la aplicacion cuando inicia
	 * 
	 * @author william corredor
	 * @date 5/11/2021
	 * @since 1.0
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		Environment env = appContext.getEnvironment();
		String protocol = "http";
		log.info(
				"\n--------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t" + "Local: \t\t{}://localhost:{}\n\t"
						+ "External: \t{}://{}:{}\n\t" + "Root Path: \t{}\n\t"
						+ "Profile(s): \t{}\n--------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, env.getProperty("server.port"), protocol,
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
				env.getProperty("server.servlet.context-path"), env.getActiveProfiles());
	}

}
