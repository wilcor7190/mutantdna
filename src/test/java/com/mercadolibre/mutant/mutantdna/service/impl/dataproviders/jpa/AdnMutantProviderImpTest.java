package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.jpa;

import com.mercadolibre.mutant.mutantdna.entity.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.mockito.Mockito.when;


class AdnMutantProviderImpTest {

	@Mock
	@Qualifier("companyMongoTemplate")
	private MongoTemplate ds;

	@InjectMocks
	private AdnMutantProviderImp adnMutantProviderImp;

	@Mock
	private AdnMutantProviderImp adnMutantProviderImp2;

	private Dna dna;

	@Mock
	private Dna dnavar;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] validDna = { "AAAA", "AGTC", "CCCC", "CTGA" };
		dna = new Dna(validDna, true);
		adnMutantProviderImp = new AdnMutantProviderImp(ds);
	}

	@Test
	void addDNASequence() throws Exception {
		adnMutantProviderImp.addDNASequence(dna);
	}

	@Test
	void saveSequence() throws Exception {

		when(ds.save(dna)).thenReturn(dna);

	}

}