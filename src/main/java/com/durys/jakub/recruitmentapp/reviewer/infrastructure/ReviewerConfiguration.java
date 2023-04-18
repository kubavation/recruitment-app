package com.durys.jakub.recruitmentapp.reviewer.infrastructure;

import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReviewerConfiguration {

    @Bean
    public ReviewerRepository reviewerRepository() {
        return new AMReviewerRepository();
    }
}
