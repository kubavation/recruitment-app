package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


class JpaOfferRepositoryTest {

    private EntityManager entityManager = Mockito.mock(EntityManager.class);

    private final JpaOfferRepository repository = new JpaOfferRepository(entityManager);

    @Test
    void shouldSuccessfullyMapAggregateToDBModel() {

        var id = UUID.randomUUID();

        var entity = OfferEntity.builder()
                .id(id)
                .description("Desc")
                .state(Offer.Status.New.name())
                .limit(1)
                .position("Position")
                .from(LocalDate.of(2023 ,1,1))
                .to(LocalDate.of(2023, 2, 1))
                .build();

        when(entityManager.find(OfferEntity.class, id)).thenReturn(entity);

        Offer offer = repository.load(new Offer.Id(id));

        assertNotNull(offer);
        assertEquals(id, offer.id().value());
        assertEquals(entity.getDescription(), offer.description());
        assertEquals(entity.getState(), offer.state().name());
        assertEquals(entity.getLimit(), offer.limit());
        assertEquals(entity.getPosition(), offer.position());
        assertEquals(entity.getFrom(), offer.from());
        assertEquals(entity.getTo(), offer.to());
    }

}