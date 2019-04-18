package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.DeveloperDao;
import com.gmail.chernii.oleksii.entity.Developer;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
public class DeveloperDaoImplementation extends EntityManagerHolder implements DeveloperDao {

    public DeveloperDaoImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void insert(Developer developer) {
        executeInsideTransaction(entityManager -> entityManager.persist(developer));
    }

    @Override
    public Developer getById(Long id) {
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
    public void removeById(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getById(id)));
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
