package be.bstorm.akimts.rest.bxl.service.impl;

import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.mapper.ResevationMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationForm;
import be.bstorm.akimts.rest.bxl.repository.EnfantRepository;
import be.bstorm.akimts.rest.bxl.repository.ReservationRepository;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<EnfantDTO> getChildrenPresentAt(LocalDateTime date){
        LocalDateTime beforeDate =  date.plusDays(1);
        return repository.findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(date,beforeDate).stream().map(Reservation::getEnfant).map(enfantMapper::toDto).toList();
    }

    public ReservationDTO create(ReservationForm reservationForm){
        if(repository.count()>=10) return null; //TODO Exception plus de disponibilité

        Reservation entity = mapper.toEntity(reservationForm);

        //TODO remplacer orElse null par une exception approprier et la gerer

        entity.setEnfant(enfantRepository.findById(reservationForm.getEnfant()).orElse(null));
        entity.setTuteurApportant(tuteurRepository.findById(reservationForm.getTuteurApportant()).orElse(null));
        entity.setTuteurRecuperant(tuteurRepository.findById(reservationForm.getTuteurRecuperant()).orElse(null));

        //TODO verifier la disponibilité (count reservation pour le jour < 10)

        //TODO check que la date d'arrvé = la date de depart sinon exception approprié
        return mapper.toDto(repository.save(entity));
    }

    public ReservationDTO cancel(Long id,String motif){
        Reservation reservationToCancel = repository.findById(id).orElse(null); //TODO exception approprié
        reservationToCancel.setAnnule(true);
        reservationToCancel.setMotifAnnulation(motif);
        return mapper.toDto(repository.save(reservationToCancel));
    }

    public List<ReservationDTO> getFuturReservationFor(Long enfantId){
        Enfant enfant = enfantRepository.findById(enfantId).orElse(null) ; //TODO exception approprié
        return repository.findByEnfantAndHeureArriveAfter(enfant,LocalDateTime.now()).stream().map(mapper::toDto).toList();
    }

    public List<ReservationDTO> getFuturReservationForThisMonth(){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime lastDayOfMonth  =  LocalDateTime.of(
                today.getYear(),
                today.getMonth(),
                today.getMonth().length(true),
                0,
                0
        );
        return repository.findByHeureArriveAfterAndHeureArriveBeforeAndAnnuleFalse(today,lastDayOfMonth).stream().map(mapper::toDto).toList();
    }

}
