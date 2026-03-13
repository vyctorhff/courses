package br.course.elite.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.course.elite.domain.StarWarFilm;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://swapi.info/api")
public interface FaultToleranceService {
    
    @GET
    @Path("/films")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(
        value = 3000L,
        unit = ChronoUnit.SECONDS // pode ser suprimido
    )
    @Fallback(fallbackMethod = "getFilmsFallback")
    @CircuitBreaker(
        requestVolumeThreshold = 2, // quantidade de request para determinar uma avaliação
        failureRatio = .5,      // Se 50% das request com problema, aba o circuito
        delay = 3000,           // tempo até para retentar novamente para verificar se a api voltou a funcionar
        successThreshold = 2    // quantidade de request para determinar se voltou ao normal
    )
    List<StarWarFilm> getFilms();

    default List<StarWarFilm> getFilmsFallback() {
        return List.of();
    }
}
