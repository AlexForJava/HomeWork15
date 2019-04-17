package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.CompanyDao;
import com.gmail.chernii.oleksii.entity.Company;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class CompanyDaoImplementation implements CompanyDao {
    private EntityManager entityManager;

    @Override
    public void insert(Company company) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(company);
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
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(company);
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
}
