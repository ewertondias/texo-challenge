package com.texo.goldenraspberryawards.infrastructure.configuration.data;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class LoadDataConfig {

	private static final String SEMICOLON_DELIMITER = ";";

	private final AwardRepository awardRepository;

	private ResourceLoader resourceLoader;


	@Value("${movelist-csv}")
	private String movielistCsv;

	public LoadDataConfig(AwardRepository awardRepository, ResourceLoader resourceLoader) {
		this.awardRepository = awardRepository;
		this.resourceLoader = resourceLoader;
	}

	@PostConstruct
	public void loadCsvData() {
		log.info("Iniciando carregamento de dados no banco");

		try {
			var resource = getClass().getClassLoader().getResourceAsStream(movielistCsv);
			Reader file = new InputStreamReader(resource);

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
			log.error("Erro ao carregar dados: {}", ex.getMessage());

			// throw new LoadDataErrorException("Erro ao carregar dados: " + ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}

	}

}
