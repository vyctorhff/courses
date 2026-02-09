package br.courses.pos.javaelite.rest.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "http://localhost:8080/time-api")
public interface TimeService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Timeout(3000) // 3 seg
    @Fallback(fallbackMethod = "getTimeApiFallback")
    @CircuitBreaker(
        requestVolumeThreshold = 4, // amount of request to check again if original service is ok
        failureRatio = .5,          // percent of request to use fallback
        delay = 500L,               // time to wait before use fallback
        successThreshold = 3        // amount of request with success to stop using ballback
    )
    String getTimeApi();

    default String getTimeApiFallback() {
        DateTimeFormatter dtf = DateTimeFormatter.BASIC_ISO_DATE;
        return "Fallback: " + LocalDateTime.now().format(dtf);
    }
}
