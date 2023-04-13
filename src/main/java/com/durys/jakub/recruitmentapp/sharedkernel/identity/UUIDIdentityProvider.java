package com.durys.jakub.recruitmentapp.sharedkernel.identity;

import java.util.UUID;

public class UUIDIdentityProvider implements IdentityProvider<UUID> {

    @Override
    public UUID identity() {
        return UUID.randomUUID();
    }
}
