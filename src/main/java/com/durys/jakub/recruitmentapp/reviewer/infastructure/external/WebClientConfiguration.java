package com.durys.jakub.recruitmentapp.reviewer.infastructure.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebClientConfiguration {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build(); //todo
    }
}
