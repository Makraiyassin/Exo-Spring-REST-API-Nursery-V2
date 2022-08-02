package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
public class EnfantDTO {

    private long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaiss;
    private boolean proprete;
    private List<String> allergies;
    private Set<TuteurDTO> tuteurs;


    public static EnfantDTO toDTO(Enfant enfant){
        EnfantDTO enfantDTO = new EnfantDTO();
        enfantDTO.setId(enfant.getId());
        enfantDTO.setNom(enfant.getNom());
        enfantDTO.setPrenom(enfant.getPrenom());
        enfantDTO.setDateNaiss(enfant.getDateNaissance());
        enfantDTO.setAllergies(enfant.getAllergies());
        enfantDTO.setProprete(enfant.isPropre());
        if(enfant.getTuteurs() != null)
            if(enfant.getTuteurs().size()>0)
                enfantDTO.setTuteurs(enfant.getTuteurs().stream().map(TuteurDTO::toDto).collect(Collectors.toSet()));
        return enfantDTO;
    }

    public EnfantDTO(long id, String nom, String prenom, LocalDate dateNaiss, boolean proprete, List<String> allergies, Set<TuteurDTO> tuteurs) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
        this.proprete = proprete;
        this.allergies = allergies;
        this.tuteurs = tuteurs;
    }
}
