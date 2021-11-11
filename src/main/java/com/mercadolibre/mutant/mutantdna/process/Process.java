package com.mercadolibre.mutant.mutantdna.process;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
/**
 * interfaz para procesar el adn
 * @author william corredor
 * @date 5/11/2021
 */
public interface Process {

	boolean processDNA(RequestMutantADN request);

}
