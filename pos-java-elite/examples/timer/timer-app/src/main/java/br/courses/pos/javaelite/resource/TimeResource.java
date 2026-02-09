package br.courses.pos.javaelite.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.courses.pos.javaelite.rest.service.TimeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/time-app")
public class TimeResource {
    
    @RestClient
    private TimeService timeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTime() {
        System.out.println("Call time api service");
        return "From API: " + timeService.getTimeApi();
    }
}
