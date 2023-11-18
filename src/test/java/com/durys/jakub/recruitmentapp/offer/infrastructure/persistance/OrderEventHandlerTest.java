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
class OrderEventHandlerTest {

    @Autowired
    private OrderEventHandler orderEventHandler;

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

        orderEventHandler.handle(event);

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

        orderEventHandler.handle(event);

        assertEquals(Offer.Status.Published.name(), entityManager.find(OfferEntity.class, id).getState());
    }

}