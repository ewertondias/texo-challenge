package com.texo.goldenraspberryawards.domain.award.service;

import com.texo.goldenraspberryawards.application.award.dto.GetProducerAwardMinMaxResponseDto;
import com.texo.goldenraspberryawards.application.award.dto.GetProducerAwardMinMaxResponseDto.GetProducerAwardIntervalResponseDto;
import com.texo.goldenraspberryawards.domain.award.GetProducerAwardIntervalUseCase;
import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetProducerAwardIntervalService implements GetProducerAwardIntervalUseCase {

	private final AwardRepository awardRepository;

	public GetProducerAwardIntervalService(AwardRepository awardRepository) {
		this.awardRepository = awardRepository;
	}

	@Override
	public GetProducerAwardMinMaxResponseDto handle() {
		var producersWinner = awardRepository.findAllWinners();

		var producersWinnerByYear = producersWinner.stream()
			.collect(Collectors.groupingBy(Award::getProducer))
			.values()
			.stream()
			.filter(producer -> producer.size() > 1)
			.sorted(Comparator.comparing(producer -> producer.get(0).getYear()))
			.toList();

		var producersDto = new ArrayList<GetProducerAwardIntervalResponseDto>();
		producersWinnerByYear.forEach(producers -> {
			for (var i = producers.size() - 1; i > 0; i--) {
				var previousYear = producers.get(i - 1).getYear();
				var followingYear = producers.get(i).getYear();

				var minInterval = Integer.parseInt(followingYear) - Integer.parseInt(previousYear);

				var producerDto = GetProducerAwardIntervalResponseDto.builder()
					.producer(producers.get(0).getProducer())
					.interval(minInterval)
					.previousWin(previousYear)
					.followingWin(followingYear)
					.build();

				producersDto.add(producerDto);
			}
		});

		var min = getProducerMinInterval(producersDto);
		var max = getProducerMaxInterval(producersDto);

		return new GetProducerAwardMinMaxResponseDto(min, max);
	}

	private GetProducerAwardIntervalResponseDto getProducerMinInterval(final List<GetProducerAwardIntervalResponseDto> producersDto) {
		return producersDto.stream()
			.min(Comparator.comparing(GetProducerAwardIntervalResponseDto::interval))
			.orElse(null);
	}

	private GetProducerAwardIntervalResponseDto getProducerMaxInterval(final List<GetProducerAwardIntervalResponseDto> producersDto) {
		return producersDto.stream()
			.max(Comparator.comparing(GetProducerAwardIntervalResponseDto::interval))
			.orElse(null);
	}

}
