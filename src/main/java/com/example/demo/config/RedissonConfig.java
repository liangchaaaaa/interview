package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RedissonConfig {

    @Bean(name = "redis-cluster")
    public RedissonClient redissonClient() {
        Config config = new Config();

        config.useClusterServers()
                .addNodeAddress(
                        "redis://127.0.0.1:7000",
                        "redis://127.0.0.1:7001",
                        "redis://127.0.0.1:7002"
                )
                .setScanInterval(2000)
                .setReadMode(ReadMode.SLAVE)
                .setSubscriptionMode(SubscriptionMode.SLAVE)
                .setSlaveConnectionMinimumIdleSize(8)
                .setMasterConnectionMinimumIdleSize(8)
                .setRetryAttempts(3)
                .setRetryInterval(1500);

        return Redisson.create(config);
    }
}