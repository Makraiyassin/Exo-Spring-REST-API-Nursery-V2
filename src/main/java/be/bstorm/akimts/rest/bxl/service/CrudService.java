package be.bstorm.akimts.rest.bxl.service;

import java.util.List;

public interface CrudService<T, TID,TInsertForm,TupdateForm> {

    // CREATE
    T create(TInsertForm toInsert);

    // UPDATE
    T update(TID id, TupdateForm toUpdate);

    // READ
    T getOne(TID id);
    List<T> getAll();

    // DELETE
    T delete(TID id);
    
}
