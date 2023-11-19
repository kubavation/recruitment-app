package com.durys.jakub.recruitmentapp.offer.infrastructure.query;

import com.durys.jakub.recruitmentapp.offer.infrastructure.query.model.OfferDTO;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OfferQueries {

    private final EntityManager entityManager;

    OfferQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<OfferDTO> findByStates(Collection<String> states, int page, int size) {

       return entityManager.createQuery("""
                select new com.durys.jakub.recruitmentapp.offer.infrastructure.query.model.OfferDTO(
                offer.id, offer.position, offer.description, offer.limit,
                offer.from, offer.to, offer.state, offer.closedAt)
                 from OfferEntity offer where offer.state in (:states)""", OfferDTO.class)
            .setFirstResult(page)
            .setMaxResults(size)
            .setParameter("states", states)
            .getResultList();

    }
}
