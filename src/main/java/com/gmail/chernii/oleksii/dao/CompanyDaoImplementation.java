package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.CompanyDao;
import com.gmail.chernii.oleksii.entity.Company;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class CompanyDaoImplementation implements CompanyDao {
    private EntityManager entityManager;

    @Override
    public void insert(Company company) {
        executeInsideTransaction(entityManager -> entityManager.persist(company));
    }

    @Override
    public Company get(Long id) {
        Company company = new Company();
        try {
            company = entityManager.find(Company.class, id);
        } finally {
            entityManager.close();
        }
        return company;
    }

    @Override
    public void update(Company company) {
        executeInsideTransaction(entityManager -> entityManager.merge(company));
    }

    @Override
    public void remove(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(get(id)));
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
