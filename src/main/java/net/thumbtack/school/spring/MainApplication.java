package net.thumbtack.school.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainApplication.class);

    public static void main(final String[] args) {
        LOGGER.info("Start application");
        SpringApplication.run(MainApplication.class);
        LOGGER.info("Stop application");
    }
}
