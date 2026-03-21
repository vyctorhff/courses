package br.course.elite.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.course.elite.domain.StarWarEvent;
import br.course.elite.domain.dto.StarWarEventDTO;

@Mapper(componentModel = "cdi")
public interface StarWarEventMapping {

    @Mapping(target = "code", expression = "java(\"by \" + event.who())")
    StarWarEventDTO toDto(StarWarEvent event);

    @Mapping(target = "code", source = "who")
    StarWarEventDTO toDto2(StarWarEvent event);
}
