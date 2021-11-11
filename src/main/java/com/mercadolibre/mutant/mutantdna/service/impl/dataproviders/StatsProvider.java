package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders;

import org.springframework.stereotype.Service;

@Service
public interface StatsProvider {

	public long getCountMutantsDNA() throws Exception;

	long getTotal() throws Exception;
}
