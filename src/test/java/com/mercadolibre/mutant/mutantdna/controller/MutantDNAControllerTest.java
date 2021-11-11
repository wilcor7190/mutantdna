package com.mercadolibre.mutant.mutantdna.controller;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.domain.ResponseMutantADN;
import com.mercadolibre.mutant.mutantdna.service.MutantDNAService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

class MutantDNAControllerTest {
	@Mock
	MutantDNAService service;

	@InjectMocks
	private MutantDNAController mutantDNAController;

	private RequestMutantADN requestvalido;
	private RequestMutantADN requestinvalido;

	private ResponseMutantADN responseMutantADNtrue;
	private ResponseMutantADN responseMutantADNfalse;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] validDna = { "ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] invalidDna = { "ÑÑÑÑÑá", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		requestvalido = new RequestMutantADN();
		requestvalido.setDna(validDna);

		requestinvalido = new RequestMutantADN();
		requestinvalido.setDna(invalidDna);

		responseMutantADNtrue = new ResponseMutantADN();
		responseMutantADNtrue.setMutant(true);

		responseMutantADNfalse = new ResponseMutantADN();
		responseMutantADNfalse.setMutant(true);

	}

	@Test
	void registrarPeticion() throws Exception {

		mutantDNAController.registrarPeticion(requestinvalido);
		mutantDNAController.registrarPeticion(requestvalido);

		when(mutantDNAController.registrarPeticion(requestinvalido)).thenReturn(Mono.just(responseMutantADNtrue));
		when(mutantDNAController.registrarPeticion(requestvalido)).thenReturn(Mono.just(responseMutantADNfalse));

	}

}