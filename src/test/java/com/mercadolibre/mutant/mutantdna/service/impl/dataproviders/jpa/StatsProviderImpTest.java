package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders.jpa;

import com.mercadolibre.mutant.mutantdna.entity.Dna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


class StatsProviderImpTest {
    @Mock
    private MongoTemplate ds;

    @InjectMocks
    private StatsProviderImp statsProviderImp;
    @Mock
    private StatsProviderImp statsProviderImp2;

    @Mock
    private Dna dnaexp;

    private Query query;

    private static final long COUNT = 10L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        query = new Query(Criteria.where("mutant").is(Boolean.TRUE));
    }

    @Test
    void getCountMutantsDNA() throws Exception {

        when(ds.count(query, Dna.class)).thenReturn(COUNT);
        Long response = statsProviderImp.getCountMutantsDNA();
        //verify(ds, times(1)).count(any(Query.class), eq(Dna.class));
        assertEquals(COUNT, response.longValue());
       // when(ds.count(any(Query.class), eq(Dna.class))).thenReturn(COUNT);
    }

    @SuppressWarnings("unchecked")
	@Test
    void getTotal() throws Exception {
        statsProviderImp.getTotal();
        when(statsProviderImp.getTotal()).thenThrow(Exception.class);
        when(statsProviderImp.getCountMutantsDNA()).thenThrow(Exception.class);
    }

    @Test
    void countDB() throws Exception {
        Query query = new Query();

        when(ds.count(query, Dna.class)).thenReturn(10L);
    }

    @Test
    void countException() throws Exception {
        doThrow(new Exception()).when(statsProviderImp2).getCountMutantsDNA();
        doThrow(new Exception()).when(statsProviderImp2).getTotal();
    }
}