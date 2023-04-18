package com.durys.jakub.recruitmentapp.reviewer.infrastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ReviewerConfiguration {

    @Bean
    @LoadBalanced
    WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient accessManagementWebClient(WebClient.Builder builder, @Value("${am-service-url}") String amServiceUrl) {
        return builder
                .baseUrl(amServiceUrl).build();
    }

    @Bean
    public ReviewerRepository reviewerRepository(WebClient accessManagementWebClient) {
        return new AMReviewerRepository(accessManagementWebClient);
    }
}
