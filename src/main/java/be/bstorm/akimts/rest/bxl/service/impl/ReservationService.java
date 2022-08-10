package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.exceptions.NoDisponibilityException;
import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.mapper.ResevationMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationForm;
import be.bstorm.akimts.rest.bxl.repository.EnfantRepository;
import be.bstorm.akimts.rest.bxl.repository.ReservationRepository;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
public class ReservationService {
    private final ResevationMapper mapper;
    private final ReservationRepository repository;
    private final EnfantRepository enfantRepository;
    private final TuteurRepository tuteurRepository;
    private final EnfantMapper enfantMapper;

    public ReservationService(ResevationMapper mapper, ReservationRepository repository, EnfantRepository enfantRepository, TuteurRepository tuteurRepository, EnfantMapper enfantMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.enfantRepository = enfantRepository;
        this.tuteurRepository = tuteurRepository;
        this.enfantMapper = enfantMapper;
    }

    public List<EnfantDTO> getChildrenPresentAt(LocalDate date){

        LocalDateTime aferDate = date.atStartOfDay();
        LocalDateTime beforeDate =  aferDate.plusDays(1);
        return repository.findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(aferDate,beforeDate).stream().map(Reservation::getEnfant).map(enfantMapper::toDto).toList();
    }

    public ReservationDTO create(ReservationForm reservationForm){
        if(reservationForm == null)   throw new RuntimeException("the form can not be null");
        if (!verifIfArrivateDateAndDepartDateIsSame(reservationForm.getHeureArrive(),reservationForm.getHeureArrive())) throw new RuntimeException("the arrivate day and depart day must be same");
        if(repository.countDisponibility(reservationForm.getHeureDepart(),reservationForm.getHeureArrive()) > 10)
            throw new NoDisponibilityException(LocalDate.from(reservationForm.getHeureDepart()));
        Reservation entity = mapper.toEntity(reservationForm);

        entity.setEnfant(enfantRepository.findById(reservationForm.getEnfant()).orElseThrow(() -> new ElementNotFoundException(Enfant.class, reservationForm.getEnfant())));
        entity.setTuteurApportant(tuteurRepository.findById(reservationForm.getTuteurApportant()).orElseThrow(() -> new ElementNotFoundException(Tuteur.class, reservationForm.getTuteurApportant())));
        entity.setTuteurRecuperant(tuteurRepository.findById(reservationForm.getTuteurRecuperant()).orElseThrow(() -> new ElementNotFoundException(Tuteur.class, reservationForm.getTuteurRecuperant())));

        return mapper.toDto(repository.save(entity));
    }

    public ReservationDTO cancel(Long id,String motif){
        Reservation reservationToCancel = repository.findById(id).orElseThrow(()->new ElementNotFoundException(Reservation.class,id));
        reservationToCancel.setAnnule(true);
        reservationToCancel.setMotifAnnulation(motif);
        return mapper.toDto(repository.save(reservationToCancel));
    }

    public List<ReservationDTO> getFuturReservationFor(Long enfantId){
        Enfant enfant = enfantRepository.findById(enfantId).orElseThrow(()->new ElementNotFoundException(Enfant.class,enfantId)) ;
        return repository.findByEnfantAndHeureArriveAfterAndAnnuleFalse(enfant,LocalDateTime.now()).stream().map(mapper::toDto).toList();
    }

    public List<ReservationDTO> getFuturReservationForThisMonth(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDayOfMonth  =  LocalDateTime.of(
                today.getYear(),
                today.getMonth(),
                today.getMonth().length(true),
                23,
                59,
                59
        );
        return repository.findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(today,lastDayOfMonth).stream().map(mapper::toDto).toList();
    }

    public boolean verifIfArrivateDateAndDepartDateIsSame(LocalDateTime arrivateDate, LocalDateTime departDate){
        LocalDate arrivate = LocalDate.of(arrivateDate.getYear(),arrivateDate.getMonth(),arrivateDate.getDayOfMonth());
        LocalDate depart = LocalDate.of(departDate.getYear(),departDate.getMonth(),departDate.getDayOfMonth());

        return arrivate.isEqual(depart);
    }

//region test check dispo
//    public boolean isAvailable(LocalDateTime arrive, LocalDateTime depart) {
//        if ( depart.isBefore(arrive) || depart.isEqual(arrive) ){
//            throw new IllegalArgumentException("The check-out date cannot be earlier than or equals to the check-in date.");
//        }
//
//        if (arrive.isBefore(LocalDateTime.now())) {
//            throw new IllegalArgumentException("The check-in date cannot be set in the past.");
//        }
//
//        for (Reservation reservation : repository.findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(arrive,depart)) {
//            if (reservation.getHeureDepart().compareTo(LocalDateTime.now()) < 0){
//                continue;
//            }
//
//            if( arrive.isBefore(reservation.getHeureDepart()) && reservation.getHeureArrive().isBefore(depart) ) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//endregion
}
