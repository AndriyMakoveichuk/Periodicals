package com.epam.periodicals.dao;

import java.util.List;

public interface Dao<T> {

    T create(T t);
    T update(T t);
    T get (int id);
    void delete(int id);
    List<T> listAll();

}
