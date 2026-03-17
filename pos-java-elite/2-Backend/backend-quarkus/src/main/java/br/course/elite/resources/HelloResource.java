package br.course.elite.resources;

import br.course.elite.service.HelloService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    @Inject
    private HelloService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/service")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloService() {
        return service.getHello();
    }
}
