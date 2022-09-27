package com.rebirth.mycode.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .removalListener((key, value, cause) -> {
                    LOG.info("Removal");
                    LOG.info("KEY: {}", key);
                    LOG.info("VALUE: {}", value);
                    LOG.info("CAUSE: {}", cause);
                })
                .evictionListener((key, value, cause) -> {
                    LOG.info("Evict");
                    LOG.info("KEY: {}", key);
                    LOG.info("VALUE: {}", value);
                    LOG.info("CAUSE: {}", cause);
                })
                .expireAfterWrite(15, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

}
