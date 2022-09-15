package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MapstructMapper {
    @Mapping(target = "proprete" , expression = "java(enfant.isPropre() ? \"propre\" : \"non-propre\"")
    EnfantDTO enfantToDto(Enfant enfant);
    Enfant enfantFormToEntity(EnfantInsertForm enfant);
}
