package com.vetispilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the VetisPilot backend.
 * Starting this class starts the web server, connects to PostgreSQL
 * and runs the database migrations (Flyway).
 */
@SpringBootApplication
public class VetisPilotApplication {

    public static void main(String[] args) {
        SpringApplication.run(VetisPilotApplication.class, args);
    }
}
