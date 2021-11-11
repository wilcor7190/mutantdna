package com.mercadolibre.mutant.mutantdna.service.impl;

import com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.StatsProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class StatsServiceImplTest {
	@Mock
	private StatsProvider db;

	private Long var;

	@InjectMocks
	private StatsServiceImpl statsServiceImpl;

	@Mock
	private StatsServiceImpl statsServiceImpl2;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		var = new Long(11);
	}

	@Test
	void getStats() throws Exception {
		when(db.getCountMutantsDNA()).thenReturn(var);
		when(db.getTotal()).thenReturn(var);
		statsServiceImpl.getStats();
		doThrow(new Exception()).when(db).getCountMutantsDNA();
		doThrow(new Exception()).when(db).getTotal();
	}

	@Test
	void getStatsExeption() throws Exception {
		doThrow(new NullPointerException("Error occurred")).when(db).getTotal();
		when(statsServiceImpl2.getStats()).thenThrow(new NullPointerException("Error occurred"));
	}
}