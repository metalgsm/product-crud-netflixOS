package com.prog.crud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "prog.crud.config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudConfig {

	private String timeZone;
	
}
