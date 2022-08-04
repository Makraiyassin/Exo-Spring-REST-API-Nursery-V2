package be.bstorm.akimts.rest.bxl.exceptions;

import be.bstorm.akimts.rest.bxl.model.entities.Enfant;
import be.bstorm.akimts.rest.bxl.model.entities.Tuteur;

public class UpdateTutorNotFoundException extends RuntimeException{
    private final Class<?> clazz;
    private final Object forId;

    public UpdateTutorNotFoundException(Class<?> clazz) {
        super("Cannot find entity {"+clazz.getSimpleName()+"}");
        this.clazz = getClazz();
        this.forId = 0;
    }

    public UpdateTutorNotFoundException(Class<?> clazz, Object forId) {
        super("Cannot find entity {"+clazz.getSimpleName()+"} by id{"+forId+"}");
        this.clazz = getClazz();
        this.forId = forId;
    }


    public UpdateTutorNotFoundException(String message, Class<?> clazz, Object forId) {
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
