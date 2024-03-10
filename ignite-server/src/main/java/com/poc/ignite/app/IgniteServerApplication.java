package com.poc.ignite.app;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class IgniteServerApplication {

	public static void main(String[] args) {

		
		ConfigurableApplicationContext context = SpringApplication.run(IgniteServerApplication.class, args);
		Ignite ignite = (Ignite) context.getBean("ignite");
		ignite.cluster().state(ClusterState.ACTIVE);

	}

}

