package com.mercadolibre.mutant.mutantdna.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class StatusCodeHandleTest {
	@InjectMocks
	private StatusCodeHandle statusCodeHandle;

	private ParametrosEntrada parametrosEntrada;

	private AdnNoMutant adnNoMutant;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		parametrosEntrada = new ParametrosEntrada("prueba mercado libre");
		adnNoMutant = new AdnNoMutant("negativatest");
	}

	@Test
	void peticionInvalida() throws JsonProcessingException {
		statusCodeHandle.peticionInvalida(parametrosEntrada);
	}

	@Test
	void adnNotMutant() throws JsonProcessingException {
		statusCodeHandle.adnNotMutant(adnNoMutant);
	}
}