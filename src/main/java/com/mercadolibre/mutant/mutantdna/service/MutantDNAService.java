package com.mercadolibre.mutant.mutantdna.service;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.domain.ResponseMutantADN;

import reactor.core.publisher.Mono;

public interface MutantDNAService {

	Mono<ResponseMutantADN> isMutant(RequestMutantADN request);

}
