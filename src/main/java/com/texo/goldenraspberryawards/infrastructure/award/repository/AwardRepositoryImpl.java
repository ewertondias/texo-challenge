package com.texo.goldenraspberryawards.infrastructure.award.repository;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import com.texo.goldenraspberryawards.infrastructure.award.assembler.AwardEntityAssembler;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AwardRepositoryImpl implements AwardRepository {

	private final AwardRepositoryJpa awardRepositoryJpa;
	private final AwardEntityAssembler awardEntityAssembler;

	public AwardRepositoryImpl(AwardRepositoryJpa awardRepositoryJpa, AwardEntityAssembler awardEntityAssembler) {
		this.awardRepositoryJpa = awardRepositoryJpa;
		this.awardEntityAssembler = awardEntityAssembler;
	}

	@Override
	public List<Award> saveAll(List<Award> awards) {
		var awardsEntities = awards.stream()
			.map(awardEntityAssembler::toEntity)
			.collect(Collectors.toList());

		var awardsSaved = awardRepositoryJpa.saveAll(awardsEntities);

		return awardsSaved.stream()
			.map(awardEntityAssembler::toModel)
			.collect(Collectors.toList());
	}

	@Override
	public List<Award> findAll() {
		return null;
	}

}
