package com.mercadolibre.mutant.mutantdna.exception;


/**
 * Clase exepcion para devolver el resultado de adn no muntae
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
public class AdnNoMutant extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AdnNoMutant(String parametro1) {
		super("El resultado de ADN mutante de la prueba es: " + parametro1);
	}

}
