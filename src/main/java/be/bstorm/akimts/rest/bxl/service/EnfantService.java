package be.bstorm.akimts.rest.bxl.service;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;

public interface EnfantService extends CrudService<EnfantDTO, Long, EnfantInsertForm, EnfantUpdateForm> {

    EnfantDTO update(Long id, EnfantUpdateForm form);
    EnfantDTO updatePart(Long id, EnfantUpdateForm form);
}
