package com.durys.jakub.recruitmentapp.registration.domain.command;

import java.util.UUID;

public sealed interface RegistrationCommand {

    record AddRegistrationOpinionCommand(UUID registrationId, String opinion) implements RegistrationCommand {}

    record ApproveRegistrationCommand(UUID registrationId) {}

    record RejectRegistrationCommand(UUID registrationId, String reason) {}

    record SubmitRegistrationCommand(UUID offerId, String firstName, String lastName, String email, String phoneNumber,
                                     String fileName, byte[] file) { }

}
