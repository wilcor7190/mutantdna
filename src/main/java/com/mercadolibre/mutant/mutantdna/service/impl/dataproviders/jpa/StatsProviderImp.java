package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.mercadolibre.mutant.mutantdna.stereotypes.DataProvider;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.StatsProvider;
import com.mercadolibre.mutant.mutantdna.entity.Dna;

/**
 * Clase stas para consultar la estadisticas en BD del adn
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Log4j2
@AllArgsConstructor
@DataProvider
public class StatsProviderImp implements StatsProvider {

	@Qualifier("companyMongoTemplate")
	private MongoTemplate ds;

	/**
	 * metodo que solo consulta la cantidad el adn que es mutante
	 * @return
	 * @throws Exception
	 */
	@Override
	public long getCountMutantsDNA() throws Exception {

		log.debug("Consultando el adn mutante");
		long count = 0;

		try {

			Query query = new Query(Criteria.where("mutant").is(Boolean.TRUE));

			Long allcount = ds.count(query, Dna.class);
			// allcount.field("mutant").equal(Boolean.TRUE);

			count = allcount;
		} catch (Exception me) {
			log.error("Error al consultar el adn mutante: " + me.getMessage());
			me.printStackTrace();
			throw new Exception("Error al consultar el adn mutante: ");
		}

		log.info("numero de mutantes encontrados: " + count);

		return count;
	}

	/**
	 * metodo que solo consulta el total  el adn
	 * @return
	 * @throws Exception
	 */
	@Override
	public long getTotal() throws Exception {

		log.debug("Consultando el total de adn");
		long count = 0;

		try {

			Query query = new Query();

			Long allcount = ds.count(query, Dna.class);

			count = allcount;

		} catch (Exception me) {
			log.error("Error al analizar el conteo del adn: " + me.getMessage());
			me.printStackTrace();
			throw new Exception("Error al analizar el conteo del adn ");
		}

		return count;
	}

}
