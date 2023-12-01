package com.durys.jakub.recruitmentapp.invitation.infrastructure;

import com.durys.jakub.recruitmentapp.invitation.domain.InvitationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InvitationConfiguration {

    @Bean
    InvitationRepository invitationRepository() {
        return new InMemoryInvitationRepository();
    }
}
