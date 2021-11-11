package com.mercadolibre.mutant.mutantdna.dto;

import lombok.Builder;
import lombok.Data;



/**
 * dto de la consulta de la BD para retornar la informacion de la estadistica de BD
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */

@Data
@Builder
public class ResponseStats {

	private long count_mutant_dna;
	private long count_human_dna;
	private double ratio;

	public ResponseStats(long mutants, long humans, double ratio) {
		this.count_mutant_dna = mutants;
		this.count_human_dna = humans;
		this.ratio = ratio;
	}

	public String toString() {
		return "[mutants: " + count_mutant_dna + ", humans: " + count_human_dna + ", ratio: " + ratio + "]";
	}

}
