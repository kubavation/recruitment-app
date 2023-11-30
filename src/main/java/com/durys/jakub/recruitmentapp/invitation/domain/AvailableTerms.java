package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;

import java.util.List;

record AvailableTerms(List<AvailableTerm> terms) {

    boolean dateValidWithAvailableTerms(Term at) {
        return terms.stream()
                .anyMatch(term -> term.inRange(at.value()));
    }
}
