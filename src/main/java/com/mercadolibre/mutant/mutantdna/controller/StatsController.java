package com.mercadolibre.mutant.mutantdna.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mercadolibre.mutant.mutantdna.dto.ResponseStats;
import com.mercadolibre.mutant.mutantdna.service.StatsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

/**
 * Clase controler donde se encuentra las capacidades que se expondra para el
 * consumo de la aplicacion para la estadistica del adn.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */

@RestController
@RequestMapping("${application.app.api.path}")
@Validated
public class StatsController {

	@Autowired
	StatsService service;

	/**
	 * metodo controler tipo post donde se devuelve  la estadistica del los adn, se implementa programacion reactiva de tipo mono para que cree su hilo propio a la solicitud
	 * @author william corredor
	 * @date 5/11/2021
	 * @return retorna la estadistica de adn en BD
	 * @throws Exception
	 */

	@GetMapping(value = "/stats", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Consulta los estadistica de los datos mutantes", response = ResponseStats.class, httpMethod = "GET")
	@ApiResponses({ @ApiResponse(code = 200, message = "Se ejecuta satisfactoriamente.") })
	public Mono<ResponseStats> statsPeticion() throws Exception {
		return service.getStats();
	}

}
