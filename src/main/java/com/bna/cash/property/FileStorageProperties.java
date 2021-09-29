package com.bna.cash.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties(prefix="file")
@Data
public class FileStorageProperties {
	
	private String uploadDir ; 
	
	

}
