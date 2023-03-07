package com.texo.goldenraspberryawards.application.award.dto;

import lombok.Builder;

public record GetProducerAwardMinMaxResponseDto(GetProducerAwardIntervalResponseDto min,
												GetProducerAwardIntervalResponseDto max) {

	@Builder
	public record GetProducerAwardIntervalResponseDto(
		String producer,
		Integer interval,
		String previousWin,
		String followingWin) {
	}

}