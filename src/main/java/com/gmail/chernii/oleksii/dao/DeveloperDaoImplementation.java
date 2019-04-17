package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.DeveloperDao;
import com.gmail.chernii.oleksii.entity.Developer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class DeveloperDaoImplementation implements DeveloperDao {
    private EntityManager entityManager;

    @Override
    public void insert(Developer developer) {
        executeInsideTransaction(entityManager -> entityManager.persist(developer));
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
        executeInsideTransaction(entityManager -> entityManager.merge(developer));
    }

    @Override
    public void remove(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(get(id)));
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

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        try {
            entityManager.getTransaction().begin();
            action.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            entityManager.getTransaction().rollback();
            log.error(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
