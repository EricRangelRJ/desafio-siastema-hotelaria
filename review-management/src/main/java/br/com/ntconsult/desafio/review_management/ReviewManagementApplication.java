package br.com.ntconsult.desafio.review_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReviewManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewManagementApplication.class, args);
	}

}
