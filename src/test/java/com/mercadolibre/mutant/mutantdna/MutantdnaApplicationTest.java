package com.mercadolibre.mutant.mutantdna;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class MutantdnaApplicationTest {

	@InjectMocks
	private MutantdnaApplication mutantdnaApplication;

	@BeforeEach
	void setUp() {
	}

	@SuppressWarnings("static-access")
	@Test
	void main() {
		mutantdnaApplication.main(new String[] {});
	}
}