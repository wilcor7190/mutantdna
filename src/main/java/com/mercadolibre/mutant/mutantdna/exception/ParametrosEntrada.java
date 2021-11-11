package com.mercadolibre.mutant.mutantdna.exception;

/**
 * Clase exepcion generico
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
public class ParametrosEntrada extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParametrosEntrada(String parametro1) {
		super("Error request entrada:" + parametro1);
	}
}