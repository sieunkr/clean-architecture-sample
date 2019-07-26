package com.example.demo.entry.amqp;

import com.example.demo.core.usecase.BlogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.Assert;

@EnableBinding(MessageListener.Sink.class)
@RequiredArgsConstructor
public class MessageListener {

    private final BlogUseCase blogUseCase;

    @StreamListener(Sink.inbound)
    public void updateContentSync(MessageContent messageContent) {

        Assert.notNull(messageContent.getQuery(), "Query is not null");

        blogUseCase.updateBlogByQuery(messageContent.getQuery());
    }

    public interface Sink {
        String inbound = "SINK-CONTENT";
        @Input(Sink.inbound)
        SubscribableChannel ContentListener();
    }
}