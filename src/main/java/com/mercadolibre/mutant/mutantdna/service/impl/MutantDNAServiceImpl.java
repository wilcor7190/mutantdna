package com.mercadolibre.mutant.mutantdna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.domain.ResponseMutantADN;
import com.mercadolibre.mutant.mutantdna.service.MutantDNAService;
import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.AdnMutantProvider;
import com.mercadolibre.mutant.mutantdna.process.Process;
import com.mercadolibre.mutant.mutantdna.process.run.ProcessDNAArray;
import com.mercadolibre.mutant.mutantdna.entity.Dna;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import com.mercadolibre.mutant.mutantdna.exception.AdnNoMutant;

/**
 * Clase servicio donde se invoca para que procese el adn y guarde a la BD el adn.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Service
@Slf4j
public class MutantDNAServiceImpl implements MutantDNAService {

	@Autowired
	private AdnMutantProvider db;

	/**
	 * metodo para procesar el adn y validar si es mutante y a su vez guardar el adn.
	 * @param request valor donde se encuentra el adn
	 * @return
	 */
	@Override
	public Mono<ResponseMutantADN> isMutant(RequestMutantADN request) {
		log.info("validar dna mutante");

		Process process = new ProcessDNAArray();
		boolean isMutant = process.processDNA(request);

		log.info("Dna procesado, resultado: " + (isMutant ? "mutant" : "human"));

		try {

			log.info("Invocando la BD");
			db.addDNASequence(new Dna(request.getDna(), isMutant));
			log.info("la secuancia de datos fue guardada correctamente");

		} catch (Exception e) {
			log.error("Error ocurred invoking database service: " + e);

		}

		if (isMutant == true) {
			return Mono.just(ResponseMutantADN.builder().isMutant(isMutant).build());
		} else {
			return Mono.error(new AdnNoMutant(isMutant ? "Positiva" : "Negativa"));
		}

	}

}
