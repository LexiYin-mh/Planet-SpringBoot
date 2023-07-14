package com.lexi.vlogapp.Service;

import java.util.Optional;
import java.util.Set;

public interface CrudService<T, ID> {

    T create(T entity);

    T update(T entity);

    Boolean delete(T entity);

    Boolean deleteById(Long id);

    T getById(ID id);

    Set<T> getAll();


}
