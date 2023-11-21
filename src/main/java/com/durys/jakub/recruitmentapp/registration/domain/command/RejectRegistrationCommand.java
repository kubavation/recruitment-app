package com.durys.jakub.recruitmentapp.registration.domain.command;

import java.util.UUID;

public record RejectRegistrationCommand(UUID registrationId, String reason) {
}
