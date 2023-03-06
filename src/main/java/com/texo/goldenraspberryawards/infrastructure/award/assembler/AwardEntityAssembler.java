package com.texo.goldenraspberryawards.infrastructure.award.assembler;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.infrastructure.award.repository.AwardEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AwardEntityAssembler {

	private final ModelMapper modelMapper;

	public AwardEntityAssembler(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public AwardEntity toEntity(final Award award) {
		return modelMapper.map(award, AwardEntity.class);
	}

	public Award toModel(final AwardEntity awardEntity) {
		return modelMapper.map(awardEntity, Award.class);
	}

}
