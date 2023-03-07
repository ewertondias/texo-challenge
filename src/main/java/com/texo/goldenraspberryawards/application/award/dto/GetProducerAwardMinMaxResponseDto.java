package com.texo.goldenraspberryawards.application.award.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;

public record GetProducerAwardMinMaxResponseDto(
	@ApiModelProperty(value = "Dados do produtor com mínimo de intervalo entre os prêmios")
	GetProducerAwardIntervalResponseDto min,
	@ApiModelProperty(value = "Dados do produtor com máximo de intervalo entre os prêmios")
	GetProducerAwardIntervalResponseDto max) {

	@Builder
	public record GetProducerAwardIntervalResponseDto(
		@ApiModelProperty(value = "Nome do produtor", example = "Allan Carr")
		String producer,

		@ApiModelProperty(value = "Intervalo entre os prêmios", example = "1")
		Integer interval,

		@ApiModelProperty(value = "Ano do primeiro prêmio", example = "1990")
		String previousWin,

		@ApiModelProperty(value = "Ano do segundo prêmio", example = "1991")
		String followingWin) {}

}