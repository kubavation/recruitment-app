package com.durys.jakub.recruitmentapp.offer.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class OrderEventHandler implements EventHandler<OfferEvent> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void handle(OfferEvent offerEvent) {

    }
}
