package be.bstorm.akimts.rest.bxl.model.forms;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationForm {
    private LocalDateTime heureArrive;
    private LocalDateTime heureDepart;
    private Long enfant;
    private Long tuteurApportant;
    private Long tuteurRecuperant;
}
