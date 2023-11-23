package com.durys.jakub.recruitmentapp.cv;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CvConfiguration {

    @Bean
    CvRepository cvRepository(EntityManager entityManager) {
        return new JpaCvRepository(entityManager);
    }
}
