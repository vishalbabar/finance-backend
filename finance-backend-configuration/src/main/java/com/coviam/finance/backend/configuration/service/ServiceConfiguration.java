package com.coviam.finance.backend.configuration.service;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vishal B on Oct, 2018
 */

@Configuration
public class ServiceConfiguration {

    @Bean
    public Mapper createDozerMapper() {
        return new DozerBeanMapper();
    }
}

