package be.bstorm.akimts.rest.bxl.model.dto;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private LocalDateTime heureArrive;
    private LocalDateTime heureDepart;
    private boolean annule;
    private String motifAnnulation;
    private EnfantDTO enfant;

    private TuteurDTO tuteurApportant;
    private TuteurDTO tuteurRecuperant;
}
