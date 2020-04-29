package com.crescentd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.crescentd.mapper")
public class EblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EblogApplication.class, args);
	}

}
