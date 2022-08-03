package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurUpdateForm;
import be.bstorm.akimts.rest.bxl.service.impl.EnfantServiceImpl;
import be.bstorm.akimts.rest.bxl.service.impl.TuteurServiceImpl;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TuteurMapper {
    private final EnfantServiceImpl enfantService;


    public TuteurMapper(EnfantServiceImpl enfantService) {
        this.enfantService = enfantService;
    }

    public Tuteur toEntity(TuteurInsertForm form){

        if( form == null )
            return null;

        Tuteur tuteur = new Tuteur();

        tuteur.setPrenom( form.getPrenom() );
        tuteur.setNom( form.getNom() );
        tuteur.setNumTel( form.getNumTel() );
        tuteur.setAdresse( form.getAdresse() );

        return tuteur;

    }

    public Tuteur toEntity(TuteurUpdateForm form){

        Tuteur entity = new Tuteur();

        entity.setPrenom(form.getPrenom());
        entity.setNom(form.getNom());
        entity.setAdresse(form.getAdresse());
        entity.setNumTel(form.getNumTel());
        entity.setEnfants(form.getEnfantsIds().stream().map(enfantService::getOne).collect(Collectors.toSet()));
        return entity;

    }

}
