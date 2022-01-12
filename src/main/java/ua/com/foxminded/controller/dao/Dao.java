package ua.com.foxminded.controller.dao;

import java.util.List;

public interface Dao<E> {

    void saveAll(List<E> list);

    void save(E entity);

    void update(E entity);

    void remove(int id);

    E getById(int id);

    E getByName(String name);

    List<E> getAll();

}
