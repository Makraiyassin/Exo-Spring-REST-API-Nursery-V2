package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.impl.EnfantServiceImpl;
import be.bstorm.akimts.rest.bxl.service.impl.TuteurServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
public class EnfantMapper {
    private EnfantServiceImpl enfantService;
    private TuteurServiceImpl tuteurService;

    public Enfant toEntity(EnfantInsertForm form){

        if( form == null )
            return null;

        Enfant enfant = new Enfant();

        enfant.setPrenom( form.getPrenom() );
        enfant.setNom( form.getNom() );
        enfant.setDateNaissance( form.getDateNaiss() );
        enfant.setPropre( form.isPropre() );
        enfant.setAllergies(form.getAllergies());

        return enfant;

    }
}
