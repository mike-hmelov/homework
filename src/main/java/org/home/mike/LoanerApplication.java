package org.home.mike;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class LoanerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoanerApplication.class, args);
	}
}
