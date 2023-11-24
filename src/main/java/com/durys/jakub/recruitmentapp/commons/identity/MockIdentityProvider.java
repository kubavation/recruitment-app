package com.durys.jakub.recruitmentapp.commons.identity;


import java.util.UUID;

class MockIdentityProvider implements IdentityProvider {

    @Override
    public UUID identifier() {
        return UUID.randomUUID();
    }
}
