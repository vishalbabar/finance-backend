package com.coviam.finance.backend.configuration.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public Mapper createDozerMapper() {
        return new DozerBeanMapper();
    }
}

