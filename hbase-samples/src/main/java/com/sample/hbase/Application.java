package com.sample.hbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Launcher.
 *
 * @author Aaric, created on 2017-05-24T17:14.
 * @since 1.0-SNAPSHOT
 */
@SpringBootApplication
//@ImportResource(locations = "classpath:spring-hbase.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
