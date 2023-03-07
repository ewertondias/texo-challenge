package com.texo.goldenraspberryawards.it;

import com.texo.goldenraspberryawards.domain.award.GetProducerAwardIntervalUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.yml")
class GetProducerAwardIntervalIT {

	@Autowired
	private GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase;

	@Test
	void deveRetornarProdutorComMenorEMaiorIntervaloEntreOsPremios() {
		var producerAward = getProducerAwardIntervalUseCase.handle();

		Assertions.assertNotNull(producerAward);
		Assertions.assertNotNull(producerAward.min());
		Assertions.assertNotNull(producerAward.max());
		Assertions.assertEquals(1, producerAward.min().interval());
		Assertions.assertEquals(6, producerAward.max().interval());
	}

}
