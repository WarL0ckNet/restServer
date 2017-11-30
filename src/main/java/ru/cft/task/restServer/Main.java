package ru.cft.task.restServer;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    final static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        log.debug("Run restServer");
        SpringApplication.run(Main.class, args);
    }
}
