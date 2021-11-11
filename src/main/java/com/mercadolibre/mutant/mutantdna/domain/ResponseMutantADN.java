package com.mercadolibre.mutant.mutantdna.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


/**
 * dominio de la clase response para restoanr el resultado adn
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Data
@Builder
@AllArgsConstructor
public class ResponseMutantADN {
	
	public ResponseMutantADN() {
	}
	
	private boolean isMutant;

}
