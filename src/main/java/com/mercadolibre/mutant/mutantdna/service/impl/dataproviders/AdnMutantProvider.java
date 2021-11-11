package com.mercadolibre.mutant.mutantdna.service.impl.dataproviders;

import org.springframework.stereotype.Service;

import com.mercadolibre.mutant.mutantdna.entity.Dna;

@Service
public interface AdnMutantProvider {

	public void addDNASequence(Dna dna) throws Exception;

}
