package com.nordea.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@SpringBootApplication
@EnableScheduling
@PropertySource("file:${nordea.config.home}/conf/app.properties")
public class NordeaAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(NordeaAssessmentApplication.class, args);
		
	}

}
