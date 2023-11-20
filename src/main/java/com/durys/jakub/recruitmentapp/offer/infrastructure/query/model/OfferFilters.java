package com.durys.jakub.recruitmentapp.offer.infrastructure.query.model;

import com.durys.jakub.recruitmentapp.commons.filters.CommonFilters;

import java.util.Set;

public class OfferFilters extends CommonFilters {

    private final Set<String> statuses;

    public OfferFilters(Set<String> statuses, Integer pageSize, Integer pageNumber) {
        super(pageSize, pageNumber);
        this.statuses = statuses;
    }

    public Set<String> statuses() {
        return statuses;
    }
}
