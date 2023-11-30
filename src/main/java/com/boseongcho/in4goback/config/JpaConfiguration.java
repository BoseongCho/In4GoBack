package com.boseongcho.in4goback.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.boseongcho.in4goback")
@EnableJpaRepositories(basePackages = "com.boseongcho.in4goback")
public class JpaConfiguration {
}
