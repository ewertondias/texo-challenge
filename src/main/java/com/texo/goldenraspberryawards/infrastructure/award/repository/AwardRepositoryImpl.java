package com.texo.goldenraspberryawards.infrastructure.award.repository;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import com.texo.goldenraspberryawards.infrastructure.award.assembler.AwardEntityAssembler;
import org.springframework.stereotype.Repository;

import java.util.List;

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
			.toList();

		var awardsSaved = awardRepositoryJpa.saveAll(awardsEntities);

		return awardsSaved.stream()
			.map(awardEntityAssembler::toModel)
			.toList();
	}

	@Override
	public List<Award> findAllWinners() {
		return awardRepositoryJpa.findAllByWinner("yes")
			.stream()
			.map(awardEntityAssembler::toModel)
			.toList();
	}

}
