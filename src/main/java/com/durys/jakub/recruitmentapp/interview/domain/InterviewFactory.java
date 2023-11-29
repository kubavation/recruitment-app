package com.durys.jakub.recruitmentapp.interview.domain;

import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.Registration;
import com.durys.jakub.recruitmentapp.sharedkernel.TenantId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InterviewFactory {

    public static Interview create(UUID registrationId, TenantId tenantId) {
        return new Interview(new Registration.Id(registrationId), tenantId);
    }

    public static Interview create(UUID id, UUID identifier, UUID registrationId,
              TenantId tenantId, String state) {

        return new Interview(
                new Interview.Id(id),
                new Identifier(identifier),
                new Registration.Id(registrationId),
                tenantId,
                Interview.State.valueOf(state));
    }

}
