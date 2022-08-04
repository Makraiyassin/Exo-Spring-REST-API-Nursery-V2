package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;

public interface EnfantService extends CrudService<Enfant, Long> {

    Enfant update(Long id, EnfantUpdateForm form);
    Enfant updatePart(Long id, EnfantUpdateForm form);
}
