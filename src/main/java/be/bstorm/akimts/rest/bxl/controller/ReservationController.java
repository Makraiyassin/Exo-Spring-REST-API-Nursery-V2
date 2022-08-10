package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.ReservationDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Reservation;
import be.bstorm.akimts.rest.bxl.model.forms.ReservationForm;
import be.bstorm.akimts.rest.bxl.service.impl.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ReservationDTO insert(@Valid @RequestBody ReservationForm form){
        return service.create(form);
    }

    @GetMapping(params = "date")
    public List<EnfantDTO> getChildrenPresentAt(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE ) LocalDate date){
        return service.getChildrenPresentAt(date);
    }

    @PatchMapping("/cancel/{id}")
    public ReservationDTO cancel(@PathVariable Long id, @RequestBody String motif){
        return service.cancel(id, motif);
    }

    @GetMapping(params = "enfantId")
    public List<ReservationDTO> getFuturReservationFor(@RequestParam Long enfantId){
        return service.getFuturReservationFor(enfantId);
    }


    @GetMapping()
    public List<ReservationDTO> getFuturReservationForThisMonth(){
        return service.getFuturReservationForThisMonth();
    }
}
