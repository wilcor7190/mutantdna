package com.mercadolibre.mutant.mutantdna.service.impl;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.entity.Dna;
import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.AdnMutantProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.mercadolibre.mutant.mutantdna.domain.ResponseMutantADN;
import static org.mockito.Mockito.when;

class MutantDNAServiceImplTest {

	@Mock
	private AdnMutantProvider db;
	private Dna dnaValidtrue;
	private Dna dnaInValidtrue;
	private Dna dnaValidfalse;
	private Dna dnaInValidfalse;

	@Mock
	private Dna dnaInValidExp;

	private RequestMutantADN requestMutantADN;

	@Mock
	private RequestMutantADN requestMutantADN2;

	private ResponseMutantADN responseMutantADN;

	private RequestMutantADN requestMutantADNfail;

	@InjectMocks
	private MutantDNAServiceImpl mutantDNAServiceImpl;

	@Mock
	private MutantDNAServiceImpl mutantDNAServiceImpl2;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] validDna = { "ATGCGA", "CAGGGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG" };
		String[] invalidDna = { "AATACT", "CCCAGA", "GGGATT", "AATTCC", "GGATCG", "TCACTG" };
		dnaValidtrue = new Dna(validDna, true);
		dnaInValidtrue = new Dna(invalidDna, true);
		dnaValidfalse = new Dna(validDna, false);
		dnaInValidfalse = new Dna(invalidDna, false);
		requestMutantADN = new RequestMutantADN();
		requestMutantADN.setDna(validDna);
		requestMutantADNfail = new RequestMutantADN();
		requestMutantADNfail.setDna(invalidDna);
		responseMutantADN = new ResponseMutantADN();
		responseMutantADN.setMutant(true);
	}

	@SuppressWarnings("unchecked")
	@Test
	void isMutant() throws Exception {
		when(mutantDNAServiceImpl.isMutant(requestMutantADNfail)).thenThrow(Exception.class);
		db.addDNASequence(dnaValidtrue);
		db.addDNASequence(dnaInValidtrue);
		db.addDNASequence(dnaValidfalse);
		db.addDNASequence(dnaInValidfalse);
		mutantDNAServiceImpl.isMutant(requestMutantADN);
		mutantDNAServiceImpl.isMutant(requestMutantADNfail);
	}

}