package be.bstorm.akimts.rest.bxl.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Tuteur extends Personne {

    @Column(nullable = false)
    private String numTel;

    @Column(nullable = false)
    private String adresse;

    @ManyToMany(mappedBy = "tuteurs")
    private Set<Enfant> enfants;

    public Tuteur(String prenom, String nom, String numTel, String adresse, Set<Enfant> enfants) {
        super(prenom, nom);
        this.numTel = numTel;
        this.adresse = adresse;
        this.enfants = enfants;
    }
}
