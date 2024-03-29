package com.durys.jakub.recruitmentapp.commons.mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MailFactory {

    public static Mail instance(String email) {
        return new Mail(email, "todo", "todo");
    }

}
