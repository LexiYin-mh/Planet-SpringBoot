package com.lexi.planet.dao.hibernateDao;

import java.util.Optional;
import java.util.Set;

public interface GenericDao<T, ID> {

    Optional<T> findById(ID id);

    Set<T> findAll();

    T save(T model);

    T update(T model);

    boolean delete(T model);

    // boolean delete(ID id);
    // One of the cons of Generic is that it cannot distinguish method overloading
    boolean deleteById(ID id);
}
