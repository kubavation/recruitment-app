package com.durys.jakub.recruitmentapp.offer.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.events.EventHandler;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent;
import com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
class OrderEventHandler implements EventHandler<OfferEvent> {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void handle(OfferEvent offerEvent) {

        log.info("Handling event {}", offerEvent);

        switch (offerEvent) {
            case OfferAdded added -> handle(added);
            case OfferPublished published -> handle(published);
            case OfferClosed closed -> handle(closed);
            default -> log.warn("Unsupported event {}", offerEvent);
        }
    }

    private void handle(OfferAdded event) {

        OfferEntity entity = new OfferEntity(event.offerId(), event.position(), event.description(), event.applicantLimit(),
                event.from(), event.to(), Offer.Status.New.name());

        entityManager.persist(entity);
    }

    private void handle(OfferPublished event) {
        OfferEntity entity = entityManager.find(OfferEntity.class, event.offerId());
        entity.setState(Offer.Status.Published.name());
    }

    private void handle(OfferClosed event) {
        OfferEntity entity = entityManager.find(OfferEntity.class, event.offerId());
        entity.setState(Offer.Status.Closed.name());
        entity.setClosedAt(event.closedAt());
    }
}
