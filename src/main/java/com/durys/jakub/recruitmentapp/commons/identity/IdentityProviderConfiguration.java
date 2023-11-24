package com.durys.jakub.recruitmentapp.commons.identity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class IdentityProviderConfiguration {

    @Bean
    IdentityProvider identityProvider() {
        return new MockIdentityProvider();
    }

}
