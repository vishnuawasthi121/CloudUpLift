package com.poc.cacheclient.app.config;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteClientConfig {
	/*
	 * @Bean public IgniteConfiguration igniteConfiguration() { // If you provide a
	 * whole ClientConfiguration bean then configuration properties // will not be
	 * used. IgniteConfiguration cfg = new IgniteConfiguration();
	 * cfg.setClientMode(true); cfg.setIgniteInstanceName("my-ignite"); return cfg;
	 * }
	 */

	
	@Bean
    IgniteClientConfigurer configurer() {
        //Setting some property.
        //Other will come from `application.yml`
        return cfg -> cfg.setSendBufferSize(64*1024);
    }
}
