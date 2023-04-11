package com.durys.jakub.recruitmentapp.ddd.annotations.events;

import com.durys.jakub.recruitmentapp.events.EventEmitter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class DomainEventRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static DomainEventRegistry instance() {
        return new DomainEventRegistry();
    }

    public void publish(DomainEvent event) {
        applicationContext
                .getBean(EventEmitter.class)
                .emit(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }
}
