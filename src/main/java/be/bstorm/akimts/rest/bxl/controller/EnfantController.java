package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.exceptions.ElementNotFoundException;
import be.bstorm.akimts.rest.bxl.exceptions.UpdateTutorNotFoundException;
import be.bstorm.akimts.rest.bxl.mapper.EnfantMapper;
import be.bstorm.akimts.rest.bxl.model.dto.EnfantDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.EnfantUpdateForm;
import be.bstorm.akimts.rest.bxl.service.EnfantService;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/enfant")
public class EnfantController {

    private final EnfantService enfantService;
    private final EnfantMapper enfantMapper;

    public EnfantController(EnfantService enfantService, EnfantMapper enfantMapper) {
        this.enfantService = enfantService;
        this.enfantMapper = enfantMapper;
    }

    @PostMapping
    public EnfantDTO insert(@RequestBody EnfantInsertForm form){
        return enfantService.create( form );
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


    @PutMapping("/{id:[0-9]+}")
    public EnfantDTO update(@PathVariable long id, @RequestBody EnfantUpdateForm form ){
        return EnfantDTO.toDTO( enfantService.update( id, form ) );
    }

    @PatchMapping("/{id:[0-9]+}")
    public EnfantDTO updatePart(@PathVariable long id, @RequestBody EnfantUpdateForm form ){
        return enfantService.updatePart( id, form );
    }

    @DeleteMapping("/{id:[0-9]+}")
    public EnfantDTO delete(@PathVariable long id){
        return  enfantService.delete(id);
    }
}
