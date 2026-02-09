package br.courses.pos.javaelite;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/time-api")
public class TimeResource {
    
    private final static DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTime() throws Exception {
        Thread.sleep(Duration.ofSeconds(5));
        return LocalDateTime.now().format(dtf);
    }
}
