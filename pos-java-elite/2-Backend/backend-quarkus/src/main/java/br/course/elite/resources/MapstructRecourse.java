package br.course.elite.resources;

import java.util.List;

import br.course.elite.domain.StarWarFilm;
import br.course.elite.domain.dto.StarWarFilmDTO;
import br.course.elite.mapping.StarWarEventMapping;
import br.course.elite.mapping.StarWarMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/mapstruct")
public class MapstructRecourse {
    
    @Inject
    StarWarMapper starWarMapping;

    @Inject
    StarWarEventMapping starWarEventMapping;

    @GET
    public Response simple() {
        StarWarFilm film = new StarWarFilm("title", List.of());
        StarWarFilmDTO dto = starWarMapping.toDto(film);

        return Response.ok(dto).build();
    }
}
