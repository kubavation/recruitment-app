package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
public class JdbcOfferRepository implements OfferRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Offer load(OfferId offerId) {
        return null;
    }

    @Override
    public void save(Offer offer) {

    }
}
