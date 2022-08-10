package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(LocalDateTime afterDate,LocalDateTime beforeDate);

    List<Reservation> findByEnfantAndHeureArriveAfterAndAnnuleFalse(Enfant enfant,LocalDateTime afterDate);

    int countAllByHeureArriveAndHeureArriveAndAnnuleFalse(LocalDateTime afterDate,LocalDateTime beforeDate);

}
