package com.durys.jakub.recruitmentapp.invitation.domain;

import com.durys.jakub.recruitmentapp.sharedkernel.AvailableTerm;

import java.time.LocalDateTime;
import java.util.List;

record AvailableTerms(List<AvailableTerm> terms) {

    boolean dateValidWithAvailableTerms(LocalDateTime at) {
        return terms.stream()
                .anyMatch(term -> term.inRange(at));
    }
}
