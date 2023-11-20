package com.durys.jakub.recruitmentapp.offer.infrastructure.in;

import com.durys.jakub.recruitmentapp.offer.infrastructure.query.OfferQueries;
import com.durys.jakub.recruitmentapp.offer.infrastructure.query.model.OfferDTO;
import com.durys.jakub.recruitmentapp.offer.infrastructure.query.model.OfferFilters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view/offers")
class OfferViewController {

    private final OfferQueries offerQueries;

    OfferViewController(OfferQueries offerQueries) {
        this.offerQueries = offerQueries;
    }

    @PostMapping
    List<OfferDTO> findOffers(@RequestBody OfferFilters filters) {
        return offerQueries.findByStates(filters);
    }
}
