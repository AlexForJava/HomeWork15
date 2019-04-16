package com.gmail.chernii.oleksii.dao;

/**
 * Created by Space on 16.04.2019.
 */
public interface DAO<T> {
    void insert(T t);

    T get(Long id);

    void update(T t);

    void remove(Long id);
}
