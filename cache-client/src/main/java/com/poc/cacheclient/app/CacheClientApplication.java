package com.poc.cacheclient.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheClientApplication {

	public static void main(String[] args) {
		// Note, that the cluster is considered inactive by default if Ignite Persistent
		// Store is used to let all the nodes join the cluster.
		// To activate the cluster call Ignite.cluster().state(ClusterState.ACTIVE).
		SpringApplication.run(CacheClientApplication.class, args);

	}

}
