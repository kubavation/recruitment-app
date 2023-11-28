package com.durys.jakub.recruitmentapp.registration.domain.command;

import java.util.UUID;

public sealed interface RegistrationCommand {

    record AddRegistrationOpinionCommand(UUID registrationId, String opinion) implements RegistrationCommand {}

    record ApproveRegistrationCommand(UUID registrationId) implements RegistrationCommand {}

    record RejectRegistrationCommand(UUID registrationId, String reason) implements RegistrationCommand {}

    record SubmitRegistrationCommand(UUID offerId, String firstName, String lastName, String email, String phoneNumber,
                                     String fileName, byte[] file)  implements RegistrationCommand { }

}
