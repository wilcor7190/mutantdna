package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mercadolibre.mutant.mutantdna.entity.Dna;
import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.AdnMutantProvider;
import com.mercadolibre.mutant.mutantdna.stereotypes.DataProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Clase servicio donde se invoca para que procese el adn y guarde a la BD el adn.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Log4j2
@AllArgsConstructor
@DataProvider
public class AdnMutantProviderImp implements AdnMutantProvider {

	@Qualifier("companyMongoTemplate")
	private MongoTemplate ds;

	/**
	 * Guarda una secuencia de adn en la base de datos. Si la secuencia ya habia
	 * sido analizada no sera guardada.
	 *
	 * @param dna secuencia de adn que se quiere guardar.
	 * @throws Exception
	 */
	@Override
	public void addDNASequence(Dna dna) throws Exception {

		log.debug("salvando la secuencia ");
		try {
			Dna x = this.ds.save(dna);
			log.debug("Dna secuencia salvada: " + x.getId());

		} catch (Exception e) {
			log.info("error al saval la secuencia: " + e.getMessage());
			log.error("Error salvando la secuencia. ", e);

		}
	}

}
