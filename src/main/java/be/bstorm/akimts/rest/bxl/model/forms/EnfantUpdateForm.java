package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class EnfantUpdateForm {
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private boolean propre;
    private List<String> allergies;
    private Set<Long> tuteurs;
}
