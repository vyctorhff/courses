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
        separator();

        String payload = message.getPayload();
        System.out.println(payload);

        return message.ack();
    }

    @Incoming("topicStarWar")
    public CompletionStage<Void> consumeStarWar(Message<StarWarEvent> message) {
        separator();

        StarWarEvent event = message.getPayload();
        System.out.println(event);

        // Optional<IncomingKafkaRecordMetadata<String, String>> metadataOpt = message.getMetadata(IncomingKafkaRecordMetadata.class);
        // if (metadataOpt.isPresent()) {
        //     IncomingKafkaRecordMetadata<String, String> metadata = metadataOpt.get();

        //     String key = (String) metadata.getKey();
        //     String topic = (String) metadata.getTopic();
        //     System.out.printf("Key: %s - Topic: %s \n", key, topic);
        // }
        
        return message.ack();
    }

    private void separator() {
        System.out.println("-".repeat(50));
        System.out.println("Message recept:");
    }
}
