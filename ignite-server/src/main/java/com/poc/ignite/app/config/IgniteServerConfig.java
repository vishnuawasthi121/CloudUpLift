package com.poc.ignite.app.config;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataPageEvictionMode;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.LoadAllWarmUpConfiguration;
import org.apache.ignite.logger.log4j2.Log4J2Logger;
import org.apache.ignite.springframework.boot.autoconfigure.IgniteAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteServerConfig {

	@Bean
	@ConfigurationProperties(prefix = IgniteAutoConfiguration.IGNITE_PROPS_PREFIX)
	public IgniteConfiguration igniteConfiguration() {
		IgniteConfiguration cfg = new IgniteConfiguration();
		DataStorageConfiguration dataStorageConfiguration = new DataStorageConfiguration();
		dataStorageConfiguration.setDefaultWarmUpConfiguration(new LoadAllWarmUpConfiguration());
		// cfg.setLifecycleBeans(new IgniteLifecycleEventBean());
		try {
			cfg.setGridLogger(log4J2Logger());
		} catch (IgniteCheckedException e) {
			e.printStackTrace();
		}

		// Default non Persistable DataRegionConfiguration config
		DataRegionConfiguration defaultDataRegionConfiguration = nonpersistentRegionConfig();
		dataStorageConfiguration.setDefaultDataRegionConfiguration(defaultDataRegionConfiguration);

		// Set another region with persistant mode on
		DataRegionConfiguration persistentRegionConfig = persistentRegionConfig();
		dataStorageConfiguration.setDataRegionConfigurations(persistentRegionConfig);

		// Create the Cache and supply them to configuration
		CacheConfiguration priceBFLCache = priceBFLCacheConfig();
		CacheConfiguration rnBFLCache = runBFLCacheConfig();
		CacheConfiguration simBFLCache = simBFLCacheConfig();
		cfg.setCacheConfiguration(priceBFLCache, rnBFLCache, simBFLCache);
		cfg.setDataStorageConfiguration(dataStorageConfiguration);
		return cfg;
	}

	public CacheConfiguration runBFLCacheConfig() {
		CacheConfiguration rnBFLCache = new CacheConfiguration("RN_BFL");
		rnBFLCache.setBackups(1);
		rnBFLCache.setCacheMode(CacheMode.REPLICATED);
		rnBFLCache.setDataRegionName("PERSISTENT_REGION");
		return rnBFLCache;
	}

	public CacheConfiguration priceBFLCacheConfig() {
		CacheConfiguration priceBFLCache = new CacheConfiguration("PRICE_BFL");
		priceBFLCache.setCacheMode(CacheMode.REPLICATED);
		priceBFLCache.setDataRegionName("NON_PERSISTENT_REGION");
		return priceBFLCache;
	}

	public CacheConfiguration simBFLCacheConfig() {
		CacheConfiguration simBFLCacheConfig = new CacheConfiguration("SIM_BFL");
		simBFLCacheConfig.setCacheMode(CacheMode.REPLICATED);
		simBFLCacheConfig.setDataRegionName("NON_PERSISTENT_REGION");
		return simBFLCacheConfig;
	}

	public DataRegionConfiguration persistentRegionConfig() {
		DataRegionConfiguration persistentRegionConfig = new DataRegionConfiguration();
		persistentRegionConfig.setName("PERSISTENT_REGION");
		persistentRegionConfig.setInitialSize(20 * 1024 * 1024);
		persistentRegionConfig.setMaxSize(40 * 1024 * 1024);
		// persistentRegionConfig.setPageEvictionMode(DataPageEvictionMode.RANDOM_2_LRU);
		persistentRegionConfig.setPersistenceEnabled(true);
		persistentRegionConfig.setPageEvictionMode(DataPageEvictionMode.RANDOM_LRU);
		return persistentRegionConfig;
	}

	// Configure non-persistent-region
	public DataRegionConfiguration nonpersistentRegionConfig() {
		DataRegionConfiguration defaultDataRegionConfiguration = new DataRegionConfiguration();
		defaultDataRegionConfiguration.setName("NON_PERSISTENT_REGION");
		// 20 MB initial size
		defaultDataRegionConfiguration.setInitialSize(20 * 1024 * 1024);
		// 40 MB maximum size
		defaultDataRegionConfiguration.setMaxSize(40 * 1024 * 1024);
		defaultDataRegionConfiguration.setPersistenceEnabled(false);
		defaultDataRegionConfiguration.setPageEvictionMode(DataPageEvictionMode.RANDOM_LRU);
		return defaultDataRegionConfiguration;
	}

	Log4J2Logger log4J2Logger() throws IgniteCheckedException {
		Log4J2Logger logger = null;
		logger = new Log4J2Logger("D:\\XVACloudPOC\\CloudUpLift\\ignite-server\\src\\main\\resources\\log4j2.xml");
		return logger;

	}

}
