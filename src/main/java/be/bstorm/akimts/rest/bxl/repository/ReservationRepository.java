package be.bstorm.akimts.rest.bxl.repository;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(LocalDateTime afterDate,LocalDateTime beforeDate);

    List<Reservation> findByEnfantAndHeureArriveAfterAndAnnuleFalse(Enfant enfant,LocalDateTime afterDate);

    @Query("SELECT COUNT(r) " +
            "FROM Reservation r " +
            "WHERE ( r.heureArrive <= ?1 AND r.heureDepart >= ?2) "
    )
    int countDisponibility(LocalDateTime depart, LocalDateTime arrive);

}
