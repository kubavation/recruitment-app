package com.durys.jakub.recruitmentapp.registration.domain;

import com.durys.jakub.recruitmentapp.commons.exception.InvalidStateForOperationException;
import com.durys.jakub.recruitmentapp.cv.CvId;
import com.durys.jakub.recruitmentapp.ddd.AggregateRoot;
import com.durys.jakub.recruitmentapp.offer.domain.Offer;
import com.durys.jakub.recruitmentapp.reviewer.domain.ReviewerId;

import static com.durys.jakub.recruitmentapp.registration.domain.events.RegistrationEvent.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Registration extends AggregateRoot {


    public record Id(UUID value) {}

    public enum Status {
        Submitted, Rejected, Approved
    }

    private final Id id;
    private final Offer.Id offerId;
    private final ApplicantInformation applicantInformation;
    private final CvId cvId;
    private RejectionReason rejectionReason;
    private Status status;
    private final Set<Review> reviews = new HashSet<>();


    Registration(Id id, Offer.Id offerId, ApplicantInformation applicantInformation, CvId cvId,
                 RejectionReason reason, Status status) {
        this.id = id;
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cvId = cvId;
        this.status = status;
        this.rejectionReason = reason;
    }

    Registration(Offer.Id offerId, ApplicantInformation applicantInformation, CvId cvId) {
        this.id = new Id(UUID.randomUUID());
        this.offerId = offerId;
        this.applicantInformation = applicantInformation;
        this.cvId = cvId;
        this.status = Status.Submitted;

        addEvent(
            new RegistrationSubmitted(
                id.value, offerId.value(), applicantInformation.firstName(), applicantInformation.lastName(),
                applicantInformation.email(), applicantInformation.phoneNumber(), cvId)
        );
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
            new RegistrationApproved(id.value, cvId) //todo creating temporary account
        );
    }

    public void addReview(ReviewerId reviewerId, String opinion) {

        reviews.removeIf(review -> review.reviewerId().equals(reviewerId));

        LocalDateTime createdAt = LocalDateTime.now();

        reviews.add(
            new Review(reviewerId, opinion, createdAt)
        );

        addEvent(
            new ReviewAdded(id.value, reviewerId, opinion, createdAt)
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

    public String getOpinion(ReviewerId reviewerId) {
        return reviews.stream()
                .filter(review -> review.reviewerId().equals(reviewerId))
                .map(Review::opinion)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }


    public Integer numberOfReviews() {
        return reviews.size();
    }

}
