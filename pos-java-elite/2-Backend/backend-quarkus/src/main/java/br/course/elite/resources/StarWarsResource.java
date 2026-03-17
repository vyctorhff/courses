package br.course.elite.resources;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.course.elite.domain.StarWarFilm;
import br.course.elite.rest.StarWarService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/starwars")
@Produces(MediaType.APPLICATION_JSON)
public class StarWarsResource {

    @RestClient
    private StarWarService starWarService;
    
    @GET
    public List<StarWarFilm> get() {
        return starWarService.getFilms();
    }
}
