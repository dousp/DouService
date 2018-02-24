package com.dsp.dou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class DouServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DouServiceApplication.class, args);
	}
}
