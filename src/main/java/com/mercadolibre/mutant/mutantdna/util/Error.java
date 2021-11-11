package com.mercadolibre.mutant.mutantdna.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Error {

	private Integer codigo;

	private String mensaje;
}
