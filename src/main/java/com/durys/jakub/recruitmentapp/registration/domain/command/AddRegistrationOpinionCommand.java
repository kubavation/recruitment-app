package com.durys.jakub.recruitmentapp.registration.domain.command;

import java.util.UUID;

public record AddRegistrationOpinionCommand(UUID registrationId, String opinion) {
}
