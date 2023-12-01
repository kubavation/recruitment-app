package com.durys.jakub.recruitmentapp.interview.infrastructure;

import com.durys.jakub.recruitmentapp.interview.domain.InterviewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InterviewConfiguration {

    @Bean
    InterviewRepository interviewRepository() {
        return new InMemoryInterviewRepository();
    }
}
