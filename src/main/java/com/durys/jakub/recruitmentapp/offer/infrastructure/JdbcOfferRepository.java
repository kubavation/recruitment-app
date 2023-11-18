package com.durys.jakub.recruitmentapp.offer.infrastructure;

import com.durys.jakub.recruitmentapp.commons.Assembler;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.offer.domain.OfferFactory;
import com.durys.jakub.recruitmentapp.offer.domain.OfferRepository;
import com.durys.jakub.recruitmentapp.offer.infrastructure.persistance.OfferEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
class JdbcOfferRepository implements OfferRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Offer load(Offer.Id offerId) {

        OfferEntity entity = jdbcTemplate
                .queryForObject("SELECT * FROM OFFER WHERE ID = " + offerId.value(), OfferEntity.class);

        return new OfferAssembler().toAggregate(entity);
    }

    @Override
    public Offer save(Offer offer) {
        return offer;
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
            return null; //todo
        }
    }

}
