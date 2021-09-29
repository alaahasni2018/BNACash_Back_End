package com.bna.cash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.bna.cash.property.FileStorageProperties;

@EnableConfigurationProperties({
	FileStorageProperties.class
})
@SpringBootApplication
public class BnacashApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnacashApplication.class, args);
	}

}
