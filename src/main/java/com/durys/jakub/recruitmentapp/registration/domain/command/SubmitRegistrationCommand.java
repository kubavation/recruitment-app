package com.durys.jakub.recruitmentapp.registration.domain.command;

import java.util.UUID;

public record SubmitRegistrationCommand(UUID offerId, String firstName, String lastName, String email,
                                        String phoneNumber, String fileName, byte[] file) {
}
