package com.wisely.ch6_5.config;

import com.wisely.ch6_5.ApplicationStartListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyApplicationConfig {
    @Bean
    public ApplicationStartListener getApplicationStartListener() {
        return new ApplicationStartListener();
    }
}
