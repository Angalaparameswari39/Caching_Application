package com.springboot.cachingapplication.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

//Ehcache cache provider requires an independent configuration to function correctly.
//so, ehcache.xml file created
@EnableCaching
@Configuration
public class CacheConfig {
}
