package be.bstorm.akimts.rest.bxl.service.impl;


import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;
import be.bstorm.akimts.rest.bxl.repository.TuteurRepository;
import be.bstorm.akimts.rest.bxl.service.TuteurService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TuteurServiceImpl implements TuteurService {
    private final TuteurRepository repository;

    public TuteurServiceImpl(TuteurRepository repository) {
        this.repository = repository;
    }


    @Override
    public Tuteur create(Tuteur toInsert) {
        if( toInsert == null)
            throw new IllegalArgumentException("inserted tutor cannot be null");

        toInsert.setId(null);

        return repository.save(toInsert);
    }

    @Override
    public Tuteur update(Long id, Tuteur toUpdate) {
        if(toUpdate == null || id == null)
            throw new IllegalArgumentException("params cannot be null");

        if( !repository.existsById(id) )
            throw new EntityNotFoundException();

        toUpdate.setId(id);

        return repository.save(toUpdate);
    }

    @Override
    public Tuteur getOne(Long id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Tuteur> getAll() {
        return repository.findAll();
    }

    @Override
    public Tuteur delete(Long id) {
        Tuteur tuteur = getOne(id);
        repository.delete(tuteur);
        tuteur.setId(0L);
        return tuteur;
    }
}
