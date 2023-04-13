package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class OfferConfiguration {

    @Bean
    OfferRepository offerRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcOfferRepository(jdbcTemplate);
    }
}
