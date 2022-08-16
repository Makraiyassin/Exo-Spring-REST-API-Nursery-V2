package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class EnfantInsertForm {

    @NotBlank
    @Size(max = 255)
    private String nom;
    @NotBlank
    @Size(max = 255)
    private String prenom;
    @PastOrPresent// (message = "autre message")
    private LocalDate dateNaiss;
    private boolean propre = true;

    public EnfantInsertForm(String nom, String prenom, LocalDate dateNaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }
}
