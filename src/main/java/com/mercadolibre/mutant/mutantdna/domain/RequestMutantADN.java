package com.mercadolibre.mutant.mutantdna.domain;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * dominio de clase adn request de entrada
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */

@Data
public class RequestMutantADN {

	public RequestMutantADN() {
	}

	@NotNull(message = "El campo dna no puede ser nulo")
	private String[] dna;

}
