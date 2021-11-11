package com.mercadolibre.mutant.mutantdna.controller;

import com.mercadolibre.mutant.mutantdna.dto.ResponseStats;
import com.mercadolibre.mutant.mutantdna.service.StatsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

class StatsControllerTest {

	@Mock
	StatsService service;

	@InjectMocks
	private StatsController statsController;

	private ResponseStats responseStats;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("static-access")
	@Test
	void statsPeticion() throws Exception {
		when(statsController.statsPeticion()).thenReturn(
				Mono.just(responseStats.builder().count_human_dna(12).count_mutant_dna(1).ratio(1).build()));

	}
}