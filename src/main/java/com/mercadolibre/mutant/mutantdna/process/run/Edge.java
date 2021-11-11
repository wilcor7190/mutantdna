package com.mercadolibre.mutant.mutantdna.process.run;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Clase entity de edge
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Data
@AllArgsConstructor
class Edge {
	private String label;
	private Node dest;

}
