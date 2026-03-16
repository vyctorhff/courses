package br.course.elite.async;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.course.elite.domain.StarWarEvent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConsumerKafka {
    
    @Incoming("topicHello")
    public CompletionStage<Void> consumeHello(Message<String> message) {
        System.out.println("-".repeat(50));
        System.out.println("Message recept:");

        String payload = message.getPayload();
        System.out.println(payload);

        return message.ack();
    }

    @Incoming("topicStarWar")
    public CompletionStage<Void> consumeStarWar(Message<StarWarEvent> message) {
        System.out.println("-".repeat(50));
        System.out.println("Message recept:");

        StarWarEvent event = message.getPayload();
        System.out.println(event);
        return message.ack();
    }
}
