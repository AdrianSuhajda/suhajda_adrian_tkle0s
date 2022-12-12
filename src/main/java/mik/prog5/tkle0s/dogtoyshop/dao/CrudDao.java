package mik.prog5.tkle0s.dogtoyshop.dao;

import java.util.List;

public interface CrudDao<T, ID> {

    T create(T object);

    T findById(ID id);

    List<T> findAll();

    T update(T object);

    boolean delete(ID id);
}
