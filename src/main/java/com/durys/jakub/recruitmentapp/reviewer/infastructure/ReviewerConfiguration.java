package com.durys.jakub.recruitmentapp.reviewer.infastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ReviewerConfiguration {

    @Bean
    ReviewerRepository reviewerRepository() {
        return new InMemoryReviewerRepository();
    }

}
