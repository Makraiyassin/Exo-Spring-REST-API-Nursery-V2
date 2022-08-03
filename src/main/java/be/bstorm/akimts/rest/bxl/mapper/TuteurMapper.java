package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
import be.bstorm.akimts.rest.bxl.service.impl.EnfantServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class TuteurMapper {
    private final EnfantServiceImpl enfantService;


    public TuteurMapper(EnfantServiceImpl enfantService) {
        this.enfantService = enfantService;
    }

    public Tuteur toEntity(TuteurForm form){

        if( form == null )
            return null;

        Tuteur tuteur = new Tuteur();

        tuteur.setPrenom( form.getPrenom() );
        tuteur.setNom( form.getNom() );
        tuteur.setNumTel( form.getNumTel() );
        tuteur.setAdresse( form.getAdresse() );

        return tuteur;

    }


}
