package com.poc.ignite.app.config;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteServerConfig {

	/**
	@Bean
	public IgniteConfiguration igniteConfiguration() {
		// If you provide a whole ClientConfiguration bean then configuration properties
		// will not be used.
		IgniteConfiguration cfg = new IgniteConfiguration();
		cfg.setClientMode(false);
		cfg.setIgniteInstanceName("my-ignite");
		return cfg;
	}
**/
	
	@Bean
	public IgniteConfigurer nodeConfigurer() {
		return cfg -> {
			// Setting some property.
			// Other will come from `application.yml`
			cfg.setIgniteInstanceName("my-ignite");
		};
	}
}
