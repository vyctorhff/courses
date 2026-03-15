package br.course.elite.async;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerHello {
    
    @Incoming("topicHello")
    public CompletionStage<Void> consume(Message<String> message) {
        String payload = message.getPayload();
        System.out.println("-".repeat(50));
        System.out.println("Message recept:");
        System.out.println(payload);
        return message.ack();
    }
}
