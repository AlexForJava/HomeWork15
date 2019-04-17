package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.DeveloperDao;
import com.gmail.chernii.oleksii.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class DeveloperDaoImplementation implements DeveloperDao {
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
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Developer get(Long id) {
        Developer developer = new Developer();
        try {
            developer = entityManager.find(Developer.class, id);
        } finally {
            entityManager.close();
        }
        return developer;
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
        } finally {
            entityManager.close();
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
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();
        try {
            TypedQuery<Developer> developerTypedQuery = entityManager.createQuery("Developer.getAll", Developer.class);
            developers = developerTypedQuery.getResultList();
        } finally {
            entityManager.close();
        }
        return developers;
    }
}
