package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationApproved;
import com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationRejected;

import java.util.UUID;

public class Registration extends AggregateRoot {

    public record Id(UUID value) {}

    public enum Status {
        Submitted, Rejected, Approved
    }

    private final Id id;
    private final Offer.Id offerId;
    private final ApplicantInformation applicantInformation;
    private final Cv cv;
    private RejectionReason rejectionReason;
    private Status status;

    Registration(Id id, Offer.Id offerId, ApplicantInformation applicantInformation, Cv cv,
                 RejectionReason reason, Status status) {
        this.id = id;
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cv = cv;
        this.status = status;
        this.rejectionReason = reason;
    }

    Registration(Offer.Id offerId, ApplicantInformation applicantInformation, Cv cv) {
        this.id = new Id(UUID.randomUUID());
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cv = cv;
        this.status = Status.Submitted;
    }

    public void reject(String reason) {

        if (status == Status.Rejected || status == Status.Approved) {
            throw new InvalidStateForOperationException("Registration cannot be rejected");
        }

        this.rejectionReason = new RejectionReason(reason);
        this.status = Status.Rejected;

        addEvent(
            new RegistrationRejected(id.value, reason)
        );
    }

    public void approve() {

        if (status == Status.Rejected || status == Status.Approved) {
            throw new InvalidStateForOperationException("Registration cannot be approved");
        }

        this.status = Status.Approved;

        addEvent(
            new RegistrationApproved(id.value) //todo creating temporary account
        );
    }

    public Id id() {
        return id;
    }

    public Status state() {
        return status;
    }

    public String rejectReason() {
        return rejectionReason.value();
    }

}
