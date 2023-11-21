package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.commons.Assembler;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferFactory;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
class JpaOfferRepository implements OfferRepository {

    private final EntityManager entityManager;

    @Override
    public Offer load(Offer.Id offerId) {

        OfferEntity entity = entityManager.find(OfferEntity.class, offerId.value());

        if (Objects.isNull(entity)) {
            throw new RuntimeException("Entity not found");
        }

        return new OfferAssembler().toAggregate(entity);
    }

    @Override
    public void save(Offer offer) {

        OfferAssembler assembler = new OfferAssembler();

        OfferEntity entity = assembler.toModel(offer);

        entityManager.persist(entity);
    }


     class OfferAssembler implements Assembler<Offer, OfferEntity> {

        @Override
        public Offer toAggregate(OfferEntity object) {
            return OfferFactory
                        .create(object.getId(), object.getPosition(), object.getDescription(),
                                object.getLimit(), object.getFrom(), object.getTo(), object.getState());
        }

        @Override
        public OfferEntity toModel(Offer aggregate) {
            return OfferEntity.builder()
                    .id(aggregate.id().value())
                    .limit(aggregate.limit())
                    .position(aggregate.position())
                    .description(aggregate.description())
                    .from(aggregate.from())
                    .to(aggregate.to())
                    .state(aggregate.state().name())
                    .build();
        }
    }

}
