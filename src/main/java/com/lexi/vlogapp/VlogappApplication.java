package com.lexi.vlogapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VlogappApplication {

	private static final Logger logger = LoggerFactory.getLogger(com.lexi.vlogapp.VlogappApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(VlogappApplication.class, args);

		logger.trace("===== VlogApp, this is a trace level logger info");
		logger.debug("===== VlogApp, this is a debug level logger info");
		logger.info("===== VlogApp, this is a info level logger info");
		logger.warn("===== VlogApp, this is a warn level logger info");
		logger.error("===== VlogApp, this is a error level logger info");

	}

}
