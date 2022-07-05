package com.co.kr.modyeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ModyeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModyeoApplication.class, args);
	}

}
