package br.course.elite.resources;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/kafka")
public class KafkaResource {
    
    @Inject
    @Channel("topicHello")
    private Emitter<String> emitterHello;


    @GET
    @Path("/hello")
    public void hello() {
        emitterHello.send("Hello world with kafka with quarkus");
    }
}
