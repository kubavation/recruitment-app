package com.durys.jakub.recruitmentapp.registration.domain;

class ApplicantInformation {

    private final Name name;
    private final Email email;
    private final PhoneNumber phoneNumber;

    ApplicantInformation(String firstName, String lastName, String email, String phoneNumber) {
        this.name = new Name(firstName, lastName);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    ApplicantInformation(Name name, Email email, PhoneNumber phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
