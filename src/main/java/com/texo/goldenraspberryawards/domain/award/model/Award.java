package com.texo.goldenraspberryawards.domain.award.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Award {

	private UUID id;
	private String year;
	private String title;
	private String studio;
	private String producer;
	private String winner;

}
