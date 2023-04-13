package com.durys.jakub.recruitmentapp.sharedkernel.identity;

import java.util.UUID;

public class UUIDDomainIdentityProvider implements DomainIdentityProvider<UUID> {

    @Override
    public UUID identity() {
        return UUID.randomUUID();
    }
}
