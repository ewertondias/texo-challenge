package com.texo.goldenraspberryawards.infrastructure.configuration.data;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDataConfig {

	private static final String SEMICOLON_DELIMITER = ";";

	private final AwardRepository awardRepository;

	public LoadDataConfig(AwardRepository awardRepository) {
		this.awardRepository = awardRepository;
	}

	@PostConstruct
	public void loadCsvData() {
		try {
			Reader file = new FileReader(ResourceUtils.getFile("classpath:movielist.csv"));

			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
				.setHeader(AwardHeadersEnum.class)
				.setSkipHeaderRecord(true)
				.setDelimiter(SEMICOLON_DELIMITER)
				.build();

			Iterable<CSVRecord> records = csvFormat.parse(file);

			List<Award> awards = new ArrayList<>();

			for (CSVRecord csvRecord: records) {
				var award = Award.builder()
					.year(csvRecord.get("year"))
					.title(csvRecord.get("title"))
					.studio(csvRecord.get("studios"))
					.producer(csvRecord.get("producers"))
					.winner(csvRecord.get("winner"))
					.build();

				awards.add(award);
			}

			awardRepository.saveAll(awards);
		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

}
