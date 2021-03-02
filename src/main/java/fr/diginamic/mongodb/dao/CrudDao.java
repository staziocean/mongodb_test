package fr.diginamic.mongodb.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    T create(T t);

    T update(ID id, T t);

    T findById(ID id);

    List<T> findAll();

    void remove(ID id);
}
