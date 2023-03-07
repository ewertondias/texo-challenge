package com.texo.goldenraspberryawards.infrastructure.award.repository;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "award")
public class AwardEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	private String year;
	private String title;
	private String studio;
	private String producer;
	private String winner;

}
