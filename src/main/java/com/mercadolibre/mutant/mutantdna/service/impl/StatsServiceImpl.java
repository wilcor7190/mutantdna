package com.mercadolibre.mutant.mutantdna.service.impl;

import com.mercadolibre.mutant.mutantdna.service.StatsService;
import com.mercadolibre.mutant.mutantdna.stereotypes.UseCase;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import com.mercadolibre.mutant.mutantdna.dto.ResponseStats;
import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.StatsProvider;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
/**
 * Clase stas para consultar la estadisticas en BD del adn
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@UseCase
@Slf4j
public class StatsServiceImpl implements StatsService {

	@Autowired
	private StatsProvider db;

	/**
	 * metodo que consulta la estadisticas de adn
	 * @return
	 * @throws Exception
	 */
	@Override
	public Mono<ResponseStats> getStats() throws Exception {

		log.info("Stats service invoked to retrieve statistics");

		long total;
		long mutants;
		double ratio = 0;

		try {
			total = db.getTotal();
			mutants = db.getCountMutantsDNA();

			if (total > 0) {
				DecimalFormat df = new DecimalFormat("#.##");
				String sRatio = df.format((mutants / (double) total));
				ratio = Double.parseDouble((sRatio).replace(",", "."));
			}

			log.debug("Statistics collected: [Total dna: " + total + ", mutants: " + (total - mutants) + ", ratio: "
					+ ratio + "]");

		} catch (Exception e) {
			log.info("Error on stats service trying to get statistics");
			log.error("Error on stats service trying to get statistics: " + e.getMessage());
			e.printStackTrace();
			throw new Exception("Error on stats service trying to get statistics");
		}

		return Mono.just(ResponseStats.builder().count_human_dna(total - mutants).count_mutant_dna(mutants).ratio(ratio)
				.build());

	}

}
