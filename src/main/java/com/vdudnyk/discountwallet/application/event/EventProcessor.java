package com.vdudnyk.discountwallet.application.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventProcessor {
    String INPUT_CHANNEL = "input";

    @Output
    MessageChannel output();

    @Input
    SubscribableChannel input();
}
