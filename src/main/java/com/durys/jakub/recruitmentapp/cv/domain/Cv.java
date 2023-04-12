package com.durys.jakub.recruitmentapp.cv.domain;

import com.durys.jakub.recruitmentapp.cv.domain.events.CvAccepted;
import com.durys.jakub.recruitmentapp.cv.domain.events.CvDeclined;
import com.durys.jakub.recruitmentapp.ddd.annotations.events.DomainEventRegistry;
import com.durys.jakub.recruitmentapp.registration.domain.RegistrationId;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
public class Cv {

    private final CvId id;
    private final RegistrationId registrationId;
    private Set<Opinion> opinions;

    public Cv(CvId id, RegistrationId registrationId) {
        this.id = id;
        this.registrationId = registrationId;
        this.opinions = Collections.emptySet();
    }

    public void approveWith(ReviewerId reviewerId, String opinion) {
        opinions.add(new Opinion(reviewerId, opinion, Opinion.Status.APPROVED));

        if (numberOfOpinionsWithStatus(Opinion.Status.APPROVED) > CvAcceptationLimitPolicyFactory.limit().approvedLimit()) {
            DomainEventRegistry
                    .instance()
                    .publish(new CvAccepted(id, registrationId));
        }

    }

    public void declineWith(ReviewerId reviewerId, String opinion) {
        opinions.add(new Opinion(reviewerId, opinion, Opinion.Status.DECLINED));

        if (numberOfOpinionsWithStatus(Opinion.Status.DECLINED) > CvAcceptationLimitPolicyFactory.limit().declinedLimit()) {
            DomainEventRegistry
                    .instance()
                    .publish(new CvDeclined(id, registrationId));
        }
    }

    private long numberOfOpinionsWithStatus(Opinion.Status status) {
        return opinions.stream()
                .filter(o -> status.equals(o.status()))
                .count();
    }

}
