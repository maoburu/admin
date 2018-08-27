package me.maoburu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("me.maoburu.dao")
@SpringBootApplication
public class AdminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
