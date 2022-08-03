package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TuteurForm {
    @NotBlank
    @Size(max = 50)
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;
}
