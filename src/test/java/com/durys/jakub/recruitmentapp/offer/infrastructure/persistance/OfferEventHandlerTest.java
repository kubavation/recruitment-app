package com.durys.jakub.recruitmentapp.offer.infrastructure.persistance;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.event.OfferEvent;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OfferEventHandlerTest {

    @Autowired
    private OfferEventHandler offerEventHandler;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    @Transactional
    void delete() {
        entityManager.createQuery("SELECT offer FROM OfferEntity offer", OfferEntity.class)
                .getResultList()
                .forEach(offer -> entityManager.remove(offer));
    }

    @Test
    void shouldCreateOrderEntity() {

        var id = UUID.randomUUID();

        var event = new OfferEvent.OfferAdded(id, "IT specialist", "Description",
                1, LocalDate.of(2023, 1, 1), null);

        offerEventHandler.handle(event);

        assertNotNull(entityManager.find(OfferEntity.class, id));
    }

    @Test
    @Transactional
    void shouldChangeStateOfOfferEntityToPublished() {

        var id = UUID.randomUUID();

        OfferEntity entity = new OfferEntity(id, "IT specialist", "Description",
                1, LocalDate.of(2023, 1, 1), null, Offer.Status.New.name());
        entityManager.persist(entity);

        var event = new OfferEvent.OfferPublished(id);

        offerEventHandler.handle(event);

        assertEquals(Offer.Status.Published.name(), entityManager.find(OfferEntity.class, id).getState());
    }

    @Test
    @Transactional
    void shouldChangeStateOfOfferEntityToClosed() {

        var id = UUID.randomUUID();
        var closedAt = LocalDate.of(2023, 10, 10).atStartOfDay();

        OfferEntity entity = new OfferEntity(id, "IT specialist", "Description",
                1, LocalDate.of(2023, 1, 1), null, Offer.Status.New.name());
        entityManager.persist(entity);

        var event = new OfferEvent.OfferClosed(id, closedAt);

        offerEventHandler.handle(event);

        var loaded = entityManager.find(OfferEntity.class, id);

        assertEquals(Offer.Status.Closed.name(), loaded.getState());
        assertEquals(closedAt, loaded.getClosedAt());
    }

}