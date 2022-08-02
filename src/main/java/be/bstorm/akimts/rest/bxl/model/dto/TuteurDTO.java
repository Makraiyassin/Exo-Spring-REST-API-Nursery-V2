package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Personne;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurInsertForm;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class TuteurDTO {
    private long id;
    private String nom;
    private String prenom;
//    private Set<EnfantDTO> enfants;
    private Set<Long> enfantsIds;

//    public static class EnfantDTO{
//        private long id;
//        private String nom;
//        private String prenom;
//        private LocalDate dateNaiss;
//        private boolean proprete;
//        private List<String> allergies;
//    }

    public static TuteurDTO toDto(Tuteur tuteur){
        TuteurDTO tuteurDTO = new TuteurDTO();
        tuteurDTO.setId(tuteur.getId());
        tuteurDTO.setNom(tuteur.getNom());
        tuteurDTO.setPrenom(tuteur.getPrenom());
        tuteurDTO.setEnfantsIds(tuteur.getEnfants().stream().map(Personne::getId).collect(Collectors.toSet()));
        return tuteurDTO;
    }
}
