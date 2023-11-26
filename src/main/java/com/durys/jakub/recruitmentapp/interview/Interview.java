package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Interview extends AggregateRoot {

    public record Id(UUID value) {
    }

    public enum State {
        NEW, PLANNED, COMPLETED
    }

    private final Id id;
    private LocalDateTime at;
    private final Registration.Id registrationId;
    private final Offer.Id offerId;
    private final TenantId tenantId;
    private ReviewerId reviewerId;

    public Interview(Id id, LocalDateTime at, Registration.Id registrationId, Offer.Id offerId, TenantId tenantId) {
        this.id = id;
        this.at = at;
        this.registrationId = registrationId;
        this.offerId = offerId;
        this.tenantId = tenantId;
    }

    public void assignReviewer(ReviewerId reviewerId) {
        //todo
        this.reviewerId = reviewerId;
    }



}
