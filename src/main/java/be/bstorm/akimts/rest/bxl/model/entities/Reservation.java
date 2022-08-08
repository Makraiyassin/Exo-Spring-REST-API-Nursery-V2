package be.bstorm.akimts.rest.bxl.model.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime heureArrive;
    private LocalDateTime heureDepart;
    private boolean annule;
    private String motifAnnulation;
    @ManyToOne
    private Enfant enfant;
    @ManyToOne
    private Tuteur tuteurApportant;
    @ManyToOne
    private Tuteur tuteurRecuperant;

    public Reservation(LocalDateTime heureArrive, LocalDateTime heureDepart, Tuteur tuteurApportant, Tuteur tuteurRecuperant) {
        this.heureArrive = heureArrive;
        this.heureDepart = heureDepart;
        this.annule = false;
        this.motifAnnulation = null;
        this.tuteurApportant = tuteurApportant;
        this.tuteurRecuperant = tuteurRecuperant;
    }

    public Reservation() {
        this.annule = false;
        this.motifAnnulation = null;
    }
}
