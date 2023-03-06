package com.texo.goldenraspberryawards.domain.award.service;

import com.texo.goldenraspberryawards.domain.award.InsertAwardUseCase;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertAwardService implements InsertAwardUseCase {

	private final AwardRepository awardRepository;

	public InsertAwardService(AwardRepository awardRepository) {
		this.awardRepository = awardRepository;
	}

}
