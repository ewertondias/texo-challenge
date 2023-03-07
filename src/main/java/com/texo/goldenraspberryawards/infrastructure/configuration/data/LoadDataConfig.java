package com.texo.goldenraspberryawards.infrastructure.configuration.data;

import com.texo.goldenraspberryawards.domain.award.model.Award;
import com.texo.goldenraspberryawards.domain.award.repository.AwardRepository;
import com.texo.goldenraspberryawards.domain.exception.LoadDataErrorException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class LoadDataConfig {

	private static final String SEMICOLON_DELIMITER = ";";

	private final AwardRepository awardRepository;

	@Value("${movelist-csv}")
	private String movielistCsv;

	public LoadDataConfig(AwardRepository awardRepository) {
		this.awardRepository = awardRepository;
	}

	@PostConstruct
	public void loadCsvData() {
		log.info("Iniciando carregamento de dados no banco");

		try {
			Reader file = new FileReader(ResourceUtils.getFile("classpath:" + movielistCsv));

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

			throw new LoadDataErrorException("Erro ao carregar dados: " + ex.getMessage());
		}

	}

}
