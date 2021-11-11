package com.mercadolibre.mutant.mutantdna.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mercadolibre.mutant.mutantdna.util.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Clase para menajar las exepciones globales de toda la aplicacion
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@ControllerAdvice
public class StatusCodeHandle {

	/**
	 * metodo para manejar las exepciones de bad request
	 * @author william corredor
	 * @date 5/11/2021
	 * @throws Exception
	 */
	@ResponseBody
	@ExceptionHandler(ParametrosEntrada.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String peticionInvalida(ParametrosEntrada exception) throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(Error.builder().codigo(400).mensaje(exception.getMessage()).build());
	}

	/**
	 * metodo para manejar las exepciones de forbiden
	 * @author william corredor
	 * @date 5/11/2021
	 * @throws JsonProcessingException
	 */
	@ResponseBody
	@ExceptionHandler(AdnNoMutant.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	String adnNotMutant(AdnNoMutant exception) throws JsonProcessingException {
		return new ObjectMapper()
				.writeValueAsString(Error.builder().codigo(403).mensaje(exception.getMessage()).build());
	}
}