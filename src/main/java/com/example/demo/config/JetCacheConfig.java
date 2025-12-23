package com.example.demo.config;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@EnableMethodCache(basePackages = "com.example.demo")
@EnableCreateCacheAnnotation
public class JetCacheConfig {
    public JetCacheConfig() {
        log.info("========== Jetcache 配置类加载 ==========");
    }
}