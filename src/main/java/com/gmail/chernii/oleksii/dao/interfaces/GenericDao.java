package com.gmail.chernii.oleksii.dao.interfaces;


/**
 * Created by Space on 16.04.2019.
 */
public interface GenericDao<T> {
    void insert(T item);

    T getById(Long id);

    void update(T item);

    void removeById(Long id);
}
