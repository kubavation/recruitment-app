package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OfferConfiguration {

    @Bean
    OfferRepository offerRepository(EntityManager entityManager) {
        return new JpaOfferRepository(entityManager);
    }
}
