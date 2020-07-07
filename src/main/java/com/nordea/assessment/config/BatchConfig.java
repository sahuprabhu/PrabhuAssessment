/*
 * Main class for Batch configuration
 * @Auther : Prabhu Sahu
 * */

package com.nordea.assessment.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Scheduled;

import com.nordea.assessment.entity.EndToEnd;
import com.nordea.assessment.model.Document;
import com.nordea.assessment.service.DocumentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing

class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private SimpleJobLauncher jobLauncher;

	JobExecution xmlReaderJobExecution;

	JobExecution cacheJobexecution;
	

	@Value("${nordea.xmlfilename}")
	private String xmlFileName;

	@Value("${nordea.xsdfilename}")
	private String xsdFileName;

	/*
	 * Reader for xmlReaderJob to Read XML File and unmarshall to POJO (Root Element "Document")
	 * */
	@Bean
	public StaxEventItemReader<Document> xmlReader() {
		log.info("Stated reading XML file");
		StaxEventItemReader<Document> reader = new StaxEventItemReader<Document>();
		reader.setResource(new ClassPathResource(xmlFileName));
		reader.setFragmentRootElementName("Document");

		Map<String, Class<?>> aliases = new HashMap<String, Class<?>>();
		aliases.put("Document", Document.class);
		Jaxb2Marshaller xStreamMarshaller = new Jaxb2Marshaller();
		xStreamMarshaller.setClassesToBeBound(Document.class);
		reader.setUnmarshaller(xStreamMarshaller);
		log.info("Finished reading XML file");
		return reader;
	}

	/*
	 * Writer for xmlReaderJob to Write EndToEndId in database
	 * */
	@Bean
	public ItemWriter<Document> xmlToDbWriter() {
		return items -> {
			items.forEach(document -> documentService.saveEndToEndId(document));
			log.info("Finished writing to DB");
		};
	}
	/*
	 * Step configuration for xmlReaderJob to Read XML File and write EndToId in database
	 * */
	@Bean
	public Step xmlReaderStep1() {
		log.info("Excuting xmlReaderStep1");
		return stepBuilderFactory.get("xmlReaderStep1").<Document, Document>chunk(10).reader(xmlReader())
				.writer(xmlToDbWriter()).build();
	}
	
	/*
	 * Reader for cacheJob to EndToEndId from database.
	 * */

	@Bean
	ItemReader<EndToEnd> databaseItemReader() {
		log.info("Excuting databaseItemReader");
		return documentService.databaseItemReader();
	}
	
	/*
	 * Writer for cacheJob  and write EndToEndId in cache.
	 * */

	@Bean
	public ItemWriter<EndToEnd> cacheWriter() {
		return items -> {
			for (EndToEnd endToEnd : items) {
				documentService.writeToCache(endToEnd);
			}
		};
	}

	/*
	 * Step configuration for cacheJob read EndToEndId from database and write in cache.
	 * */
	@Bean
	public Step cacheStep1() {
		return stepBuilderFactory.get("cacheStep1").<EndToEnd, EndToEnd>chunk(10).reader(databaseItemReader())
				.writer(cacheWriter()).build();
	}

	/*
	 * Job configuration to parse xml and save EndToEndId in Database.
	 * */
	@Bean
	public Job xmlReaderJob() {
		return jobBuilderFactory.get("xmlReaderJob").incrementer(new RunIdIncrementer()).flow(xmlReaderStep1()).end()
				.build();

	}

	/*
	 * Job configuration to read EndToEndId from Database and write it to cache.
	 * */
	@Bean
	public Job cacheJob() {
		return jobBuilderFactory.get("cacheJob").incrementer(new RunIdIncrementer()).flow(cacheStep1()).end().build();

	}

	/*
	 * Sheduling job with cron.
	 * */
	@Scheduled(cron = "0 */1 * * * *")
	public void perform() throws Exception {

		/*
		 * Checks if xml is valid else does not trigger the job. 
		 * 
		 * */
		if (documentService.validateXml(xmlFileName, xsdFileName)) {
			log.info("xmlReaderJob Job Started at :" + new Date());
			xmlReaderJobExecution = jobLauncher.run(xmlReaderJob(), new JobParameters());

			log.info("xmlReaderJob Job Finished at :" + new Date());
			log.info("xmlReaderJob Job finished with status :" + xmlReaderJobExecution.getStatus());
		}

		cacheJobexecution = jobLauncher.run(cacheJob(), new JobParameters());

		log.info("cacheJob Job Finished at :" + new Date());
		log.info("cacheJob Job finished with status :" + cacheJobexecution.getStatus());
	}
	/*
	 * Job Luncher configuration.
	 * */
	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
}
