package com.durys.jakub.recruitmentapp.registration.infrastructure.persistance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "OFFER_REGISTRATION")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Data
@Builder
@AllArgsConstructor
public class RegistrationEntity {

    @Id
    private UUID id;

    @Column(name = "APPLICANT_FIRST_NAME")
    private String applicantFirstName;

    @Column(name = "APPLICANT_LAST_NAME")
    private String applicantLastName;

    @Column(name = "APPLICANT_LAST_NAME")
    private String applicantEmail;

    @Column(name = "APPLICANT_PHONE_NUMBER")
    private String applicantPhoneNumber;

    private String status;

    @Column(name = "REJECTION_REASON")
    private String rejectionReason;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE")
    private byte[] file;

    RegistrationEntity(UUID id, String applicantFirstName, String applicantLastName,
                       String applicantEmail, String applicantPhoneNumber, String status,
                       String rejectionReason, String fileName, byte[] file) {
        this.id = id;
        this.applicantFirstName = applicantFirstName;
        this.applicantLastName = applicantLastName;
        this.applicantEmail = applicantEmail;
        this.applicantPhoneNumber = applicantPhoneNumber;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.fileName = fileName;
        this.file = file;
    }
}
