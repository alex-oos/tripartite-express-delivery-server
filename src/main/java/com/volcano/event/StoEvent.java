package com.volcano.event;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class StoEvent extends ApplicationEvent {

    public StoEvent(Object source) {

        super(source);
    }

    public StoEvent(Object source, Clock clock) {

        super(source, clock);
    }

}
