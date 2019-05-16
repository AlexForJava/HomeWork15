package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.CompanyDao;
import com.gmail.chernii.oleksii.entity.Company;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
public class CompanyDaoImplementation extends EntityManagerHolder implements CompanyDao {

    public CompanyDaoImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void insert(Company company) {
        executeInsideTransaction(entityManager -> entityManager.persist(company));
    }

    @Override
    public Company getById(Long id) {
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
    public void removeById(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getById(id)));
    }
}
