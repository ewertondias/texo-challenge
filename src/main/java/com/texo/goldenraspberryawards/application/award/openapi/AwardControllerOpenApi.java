package com.texo.goldenraspberryawards.application.award.openapi;

import com.texo.goldenraspberryawards.application.award.dto.GetProducerAwardMinMaxResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Premios")
public interface AwardControllerOpenApi {

	@ApiOperation("Retorna lista de produtores com intervalos entre os prêmios")
	GetProducerAwardMinMaxResponseDto getProducerAwardInterval();

}
