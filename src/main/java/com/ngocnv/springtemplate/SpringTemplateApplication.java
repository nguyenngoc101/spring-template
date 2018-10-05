package com.ngocnv.springtemplate;

import com.ngocnv.springtemplate.entity.ToDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringTemplateApplication {

	static final Logger logger = LoggerFactory.getLogger(SpringTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringTemplateApplication.class, args);
		logger.info("info: create new todo: ");
		logger.warn("warn: create new todo: ");
		logger.debug("debug: create new todo: ");
		logger.error("error: create new todo: ");
	}
}
