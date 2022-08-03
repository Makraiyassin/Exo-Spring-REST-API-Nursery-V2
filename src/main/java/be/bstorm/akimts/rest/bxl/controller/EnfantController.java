package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
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

    private final EnfantService enfantService;
    private final TuteurService tuteurService;
    private final EnfantMapper enfantMapper;

    public EnfantController(EnfantService enfantService, TuteurService tuteurService, EnfantMapper enfantMapper) {
        this.enfantService = enfantService;
        this.tuteurService = tuteurService;
        this.enfantMapper = enfantMapper;
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping("/{id:[0-9]+}")
    public EnfantDTO getOne(@PathVariable long id){
        return EnfantDTO.toDTO( enfantService.getOne(id) );
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500/")
    @GetMapping({"", "/all"})
    public List<EnfantDTO> getAll(){
        return enfantService.getAll().stream()
                .map( EnfantDTO::toDTO )
                .toList();
    }

    @PostMapping
    public EnfantDTO insert(@RequestBody EnfantInsertForm form){
        Enfant enfant = enfantMapper.toEntity(form);
        if(form.getTuteurs()!= null)
            enfant.setTuteurs(form.getTuteurs().stream().map(tuteurService::getOne).collect(Collectors.toSet()));

        return EnfantDTO.toDTO( enfantService.create( enfant ) );
    }

    @DeleteMapping("/{id}")
    public EnfantDTO delete(@PathVariable long id){
        return EnfantDTO.toDTO( enfantService.delete(id) );
    }

    @PutMapping("/{id}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form ){
        Enfant enfant = enfantMapper.toEntity(form);
        if(form.getTuteurs()!= null)
            enfant.setTuteurs(form.getTuteurs().stream().map(tuteurService::getOne).collect(Collectors.toSet()));

        return EnfantDTO.toDTO( enfantService.update( id, enfant ) );
    }

}
