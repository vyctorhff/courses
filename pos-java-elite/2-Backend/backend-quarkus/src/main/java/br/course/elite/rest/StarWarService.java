package br.course.elite.rest;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import br.course.elite.domain.StarWarFilm;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://swapi.info/api")
public interface StarWarService {
    
    @GET
    @Path("/films")
    @Produces(MediaType.APPLICATION_JSON)
    List<StarWarFilm> getFilms();
}
