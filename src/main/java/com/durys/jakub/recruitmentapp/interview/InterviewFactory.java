package com.durys.jakub.recruitmentapp.interview;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.ReviewerId;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;

import java.time.LocalDateTime;
import java.util.UUID;

public class InterviewFactory {

    public static Interview create(UUID registrationId, UUID offerId, TenantId tenantId) {
        return new Interview(new Registration.Id(registrationId), new Offer.Id(offerId), tenantId);
    }

    public static Interview create(UUID id, LocalDateTime at, UUID registrationId, UUID offerId,
              TenantId tenantId, UUID reviewerId, String state) {

        return new Interview(
                new Interview.Id(id),
                at,
                new Registration.Id(registrationId),
                new Offer.Id(offerId),
                tenantId,
                new ReviewerId(reviewerId),
                Interview.State.valueOf(state));
    }

}
