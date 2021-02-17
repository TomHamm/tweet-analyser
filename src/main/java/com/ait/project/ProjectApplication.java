package com.ait.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ProjectApplication {
	public static void main(String[] args) throws InterruptedException, IOException {

		SpringApplication.run(ProjectApplication.class, args);

		String url = "curl -X POST http://localhost:8080/api/v1/tweets";

		while(true) {
			Process process = Runtime.getRuntime().exec(url);
			Thread.sleep(900000);
		}
	}
}