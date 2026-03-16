package br.course.elite.resources;

import java.time.LocalDateTime;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import br.course.elite.domain.StarWarEvent;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/kafka")
public class KafkaResource {
    
    @Inject
    @Channel("topicHello")
    private Emitter<String> emitterHello;

    @Inject
    @Channel("topicStarWar")
    private Emitter<StarWarEvent> emitterStarWar;

    @GET
    @Path("/hello")
    public void hello() {
        emitterHello.send("Hello world with kafka with quarkus");
    }

    @GET
    @Path("/hello-key")
    public void helloKey() {
        KafkaRecord<String, String> message = KafkaRecord.of("message-key-1", "Hello world with key");
        emitterHello.send(message);
    }

    @GET
    @Path("/object")
    public void object() {
        StarWarEvent event = new StarWarEvent("darth vader", LocalDateTime.now());
        emitterStarWar.send(event);
    }
}
