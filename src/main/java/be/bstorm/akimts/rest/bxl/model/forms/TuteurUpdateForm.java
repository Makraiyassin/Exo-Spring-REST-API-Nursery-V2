package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class TuteurUpdateForm {
    @NotBlank
    @Size(max = 50)
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;
    private Set<Enfant> enfants;

    public Tuteur toEntity(){
        Tuteur tuteur = new Tuteur();
        tuteur.setNom(nom);
        tuteur.setPrenom(prenom);
        tuteur.setAdresse(adresse);
        tuteur.setNumTel(numTel);
        tuteur.setEnfants(enfants);

        return tuteur;
    }

}
