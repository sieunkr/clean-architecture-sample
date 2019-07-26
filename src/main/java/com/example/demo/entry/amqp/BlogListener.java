package com.example.demo.entry.amqp;

import com.example.demo.core.usecase.BlogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(BlogListener.Sink.class)
@RequiredArgsConstructor
public class BlogListener {

    private final BlogUseCase blogUseCase;

    @StreamListener(Sink.inbound)
    public void updateContentSync(MessageContent messageContent) {
        blogUseCase.updateBlogByQuery(messageContent.getQuery());
    }

    public interface Sink {
        String inbound = "SINK-CONTENT";
        @Input(Sink.inbound)
        SubscribableChannel ContentListener();
    }
}