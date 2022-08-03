package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import org.springframework.stereotype.Component;

@Component

public class EnfantMapper {
    public Enfant toEntity(EnfantInsertForm form){

        if( form == null )
            return null;

        Enfant enfant = new Enfant();

        enfant.setPrenom( form.getPrenom() );
        enfant.setNom( form.getNom() );
        enfant.setDateNaissance( form.getDateNaiss() );
        enfant.setPropre( form.isPropre() );

        return enfant;

    }

    // Il est a remarquer qu'on ne mappe pas l'id ou les tuteurs
    public Enfant toEntity(EnfantUpdateForm form){
        if( form == null )
            return null;

        Enfant entity = new Enfant();

        entity.setPrenom(form.getPrenom());
        entity.setNom(form.getNom());
        entity.setDateNaissance(form.getDateNaiss());
        entity.setPropre(form.isPropre());
        entity.setAllergies(form.getAllergies());
        return entity;

    }
}
