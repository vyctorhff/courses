package br.course.elite.mapping;

import org.mapstruct.Mapper;

import br.course.elite.domain.StarWarFilm;
import br.course.elite.domain.dto.StarWarFilmDTO;

@Mapper(componentModel = "jakarta-cdi")
public interface StarWarMapper {

    // domain to dto
    StarWarFilmDTO toDto(StarWarFilm film);

    // dto to domain
    StarWarFilm fromDto(StarWarFilmDTO dto);
}
