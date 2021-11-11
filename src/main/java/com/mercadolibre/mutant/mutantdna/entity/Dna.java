package com.mercadolibre.mutant.mutantdna.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



/**
 * Clase entidad de dna 
 * consumo de la aplicacion para la estadistica del adn.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@Document(collection = "mutant")
public class Dna {

	@Id
	@Field("_id")
	@JsonProperty("_id")
	private String id;

	@Indexed(unique = true)
	private Sequence sequence;

	private boolean mutant;

	public Dna(String[] dna, boolean isMutant) {
		this.sequence = new Sequence(dna);
		this.mutant = isMutant;
	}

	private static class Sequence {
		@SuppressWarnings("unused")
		private String[] dna;

		public Sequence(String[] seq) {
			this.dna = seq;
		}

	}

}
