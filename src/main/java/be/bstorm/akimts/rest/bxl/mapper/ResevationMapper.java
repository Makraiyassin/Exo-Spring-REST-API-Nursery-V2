package be.bstorm.akimts.rest.bxl.mapper;

import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationForm;
import org.springframework.stereotype.Component;

@Component
public class ResevationMapper {
    private final TuteurMapper tuteurMapper;
    private final EnfantMapper enfantMapper;

    public ResevationMapper(TuteurMapper tuteurMapper, EnfantMapper enfantMapper) {
        this.tuteurMapper = tuteurMapper;
        this.enfantMapper = enfantMapper;
    }


    public Reservation toEntity(ReservationForm form){

        Reservation entity = new Reservation();
        entity.setHeureArrive(form.getHeureArrive());
        entity.setHeureDepart(form.getHeureDepart());
        return entity;
    }

    public ReservationDTO toDto(Reservation entity){

        ReservationDTO dto = new ReservationDTO();
        dto.setId(entity.getId());
        dto.setHeureDepart(entity.getHeureDepart());
        dto.setHeureArrive(entity.getHeureArrive());

        dto.setEnfant(enfantMapper.toDto(entity.getEnfant()));
        dto.setTuteurApportant(tuteurMapper.toDto(entity.getTuteurApportant()));
        dto.setTuteurRecuperant(tuteurMapper.toDto(entity.getTuteurRecuperant()));
        dto.setAnnule(entity.isAnnule());
        dto.setMotifAnnulation(entity.getMotifAnnulation());
        return dto;
    }
}
