package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enfant")
public class EnfantController {

    private final EnfantService service;
    private final TuteurService tuteurService;
    private final EnfantMapper mapper;

    public EnfantController(EnfantService service, TuteurService tuteurService, EnfantMapper mapper) {
        this.service = service;
        this.tuteurService = tuteurService;
        this.mapper = mapper;
    }

    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){
        return EnfantDTO.toDTO( service.getOne(id) );
    }

    @GetMapping({"", "/all"})
    public List<EnfantDTO> getAll(){
        return service.getAll().stream()
                .map( EnfantDTO::toDTO )
                .toList();
    }

    @PostMapping
    public EnfantDTO insert(@RequestBody EnfantInsertForm form){
        Enfant entity = mapper.toEntity(form);
        entity = service.create( entity );
        return EnfantDTO.toDTO( entity );
    }

    @DeleteMapping("/{id}")
    public EnfantDTO delete(@PathVariable long id){
        return EnfantDTO.toDTO( service.delete(id) );
    }

    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form ){
        Enfant enfant = new Enfant();
        enfant.setPrenom(form.getPrenom());
        enfant.setNom(form.getNom());
        enfant.setDateNaissance(form.getDateNaiss());
        enfant.setPropre(form.isPropre());
        enfant.setAllergies(form.getAllergies());
        if(form.getTuteurs()!= null)
            enfant.setTuteurs(form.getTuteurs().stream().map(tuteurService::getOne).collect(Collectors.toSet()));

//        Enfant entity = mapper.toEntity(form);
        return EnfantDTO.toDTO( service.update( id, enfant ) );

    }

}
