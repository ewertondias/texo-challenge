package com.texo.goldenraspberryawards.application.award.rest;

import com.texo.goldenraspberryawards.application.award.dto.GetProducerAwardMinMaxResponseDto;
import com.texo.goldenraspberryawards.application.award.openapi.AwardControllerOpenApi;
import com.texo.goldenraspberryawards.domain.award.GetProducerAwardIntervalUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = AwardController.PATH)
public class AwardController implements AwardControllerOpenApi {

	public static final String PATH = "awards";

	private final GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase;

	public AwardController(GetProducerAwardIntervalUseCase getProducerAwardIntervalUseCase) {
		this.getProducerAwardIntervalUseCase = getProducerAwardIntervalUseCase;
	}

	@Override
	@GetMapping
	public GetProducerAwardMinMaxResponseDto getProducerAwardInterval() {
		return getProducerAwardIntervalUseCase.handle();
	}

}
