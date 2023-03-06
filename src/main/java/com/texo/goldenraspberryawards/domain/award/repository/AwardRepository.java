package com.texo.goldenraspberryawards.domain.award.repository;

import com.texo.goldenraspberryawards.domain.award.model.Award;

import java.util.List;

public interface AwardRepository {

	List<Award> saveAll(final List<Award> award);

	List<Award> findAll();

}
