package com.mercadolibre.mutant.mutantdna.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercadolibre.mutant.mutantdna.service.MutantDNAService;
import org.springframework.http.MediaType;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.domain.ResponseMutantADN;
import reactor.core.publisher.Mono;

/**
 * Clase controller donde se reciben las peticiones del servicio y se expone el swagger
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */

@RestController
@RequestMapping("${application.app.api.path}")
@Validated
public class MutantDNAController {

	@Autowired
	MutantDNAService service;

	
	/**
	 * metodo controler tipo post donde se recibe el adn, se implementa programacion reactiva de tipo mono para que cree su hilo propio a la solicitud
	 * @author william corredor
	 * @date 5/11/2021
	 * @param request entrada de adn
	 * @return retorna si el adn es mutanto o no
	 * @throws Exception
	 */
	@PostMapping(value = "/mutants", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Solicitud adn mutante", response = ResponseMutantADN.class, httpMethod = "POST")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuto satisfactoriamente.") })
	public Mono<ResponseMutantADN> registrarPeticion(@Valid @RequestBody RequestMutantADN request) throws Exception {
		return service.isMutant(request);
	}

}
