package com.mercadolibre.mutant.mutantdna.service;

import com.mercadolibre.mutant.mutantdna.dto.ResponseStats;

import reactor.core.publisher.Mono;

public interface StatsService {

	Mono<ResponseStats> getStats() throws Exception;

}
