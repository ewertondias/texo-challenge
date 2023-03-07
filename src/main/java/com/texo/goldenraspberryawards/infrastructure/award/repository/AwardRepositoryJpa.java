package com.texo.goldenraspberryawards.infrastructure.award.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepositoryJpa extends JpaRepository<AwardEntity, Long> {

	List<AwardEntity> findAllByWinner(final String winner);

}
