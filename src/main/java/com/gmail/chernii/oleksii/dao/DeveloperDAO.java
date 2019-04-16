package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class DeveloperDAO implements DAO<Developer> {
    private EntityManager entityManager;

    @Override
    public void insert(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(developer);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }
    }

    @Override
    public Developer get(Long id) {
        return entityManager.find(Developer.class, id);
    }

    @Override
    public void update(Developer developer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(developer);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }
    }

    @Override
    public void remove(Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(get(id));
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }
    }
}
