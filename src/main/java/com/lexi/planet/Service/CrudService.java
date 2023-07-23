package com.lexi.planet.Service;

import java.util.Set;

public interface CrudService<T, ID> {

    T create(T Dto);

    T update(T Dto);

    Boolean delete(T Dto);

    Boolean deleteById(Long id);

    T getById(ID id);

    Set<T> getAll();


}
