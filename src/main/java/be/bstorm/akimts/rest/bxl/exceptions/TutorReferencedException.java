package be.bstorm.akimts.rest.bxl.exceptions;

import be.bstorm.akimts.rest.bxl.model.entities.Personne;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;

import java.util.stream.Collectors;

public class TutorReferencedException extends RuntimeException {

    private final Class<?> clazz;
    private final Object forId;

    public TutorReferencedException(Tuteur tuteur) {
        super("tutor  {"+tuteur.getNom()+"} for id {"+ tuteur.getId() +"} is referenced to child(s) {"+tuteur.getEnfants().stream().map(Personne::getNom).collect(Collectors.toSet())+"} by id(s) {"+ tuteur.getEnfants().stream().map(Personne::getId).collect(Collectors.toSet())+"}!!!");
        this.clazz = tuteur.getClass();
        this.forId = tuteur.getId();
    }

    public TutorReferencedException(String message, Class<?> clazz, Object forId) {
        super(message);
        this.clazz = clazz;
        this.forId = forId;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public Object getForId() {
        return forId;
    }
}
