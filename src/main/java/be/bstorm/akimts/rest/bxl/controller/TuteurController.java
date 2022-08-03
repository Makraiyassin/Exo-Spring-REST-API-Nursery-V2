package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.mapper.TuteurMapper;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurInsertForm;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurUpdateForm;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tuteur")
public class TuteurController {

    private final TuteurService service;
    private final TuteurMapper mapper;

    public TuteurController(TuteurService service, TuteurMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id:[0-9]+}")
    public TuteurDTO getOne(@PathVariable long id){
        return TuteurDTO.toDto( service.getOne(id) );
    }

    @GetMapping({"", "/all"})
    public List<TuteurDTO> getAll(){
        return service.getAll().stream()
                .map( TuteurDTO::toDto )
                .toList();
    }

    @PostMapping
    public TuteurDTO insert(@RequestBody TuteurInsertForm form){
        Tuteur entity = mapper.toEntity(form);
        entity = service.create( entity );
        return TuteurDTO.toDto( entity );
    }

    @DeleteMapping("/{id}")
    public TuteurDTO delete(@PathVariable long id){
        return TuteurDTO.toDto( service.delete(id) );
    }

    @PutMapping("/{id}")
    public TuteurDTO update(@PathVariable long id, @RequestBody TuteurUpdateForm form ){

        Tuteur entity = mapper.toEntity(form);
        return TuteurDTO.toDto( service.update( id, entity ) );

    }

}
