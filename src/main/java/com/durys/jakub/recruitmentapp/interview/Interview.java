package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.UUID;

public class Interview {

    public record Id(UUID value) {
    }

    private final Id id;
    private final LocalDateTime at;
    private final Registration.Id registrationId;
    private final Offer.Id offerId;
    private final TenantId tenantId;
    private ReviewerId reviewerId;

}
