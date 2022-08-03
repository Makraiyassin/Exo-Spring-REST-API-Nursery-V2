package be.bstorm.akimts.rest.bxl.model.forms;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class EnfantInsertForm {

    @NotBlank
    @Size(max = 50)
    private String nom;
    private String prenom;
    @PastOrPresent
    private LocalDate dateNaiss;
    private boolean propre;
    private List<String> allergies;
    private Set<Long> tuteurs;

}
