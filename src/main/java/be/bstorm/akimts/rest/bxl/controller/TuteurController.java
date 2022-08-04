package be.bstorm.akimts.rest.bxl.controller;

import be.bstorm.akimts.rest.bxl.mapper.TuteurMapper;
import be.bstorm.akimts.rest.bxl.model.dto.TuteurDTO;
import be.bstorm.akimts.rest.bxl.model.forms.TuteurForm;
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
    @PostMapping
    public TuteurDTO insert(@RequestBody TuteurForm form){
        return TuteurDTO.toDto( service.create( mapper.toEntity(form) ) );
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

    @PutMapping("/{id:[0-9]+}")
    public TuteurDTO update(@PathVariable long id, @RequestBody TuteurForm form ){
        return TuteurDTO.toDto( service.update( id, mapper.toEntity(form) ) );

    }

    @DeleteMapping("/{id:[0-9]+}")
    public TuteurDTO delete(@PathVariable long id){
        return TuteurDTO.toDto( service.delete(id) );
    }

}
