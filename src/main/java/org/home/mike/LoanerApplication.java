package org.home.mike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LoanerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanerApplication.class, args);
    }
}
