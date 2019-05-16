package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.GenericDao;
import com.gmail.chernii.oleksii.entity.Customer;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
public class CustomerDaoImplementation extends EntityManagerHolder implements GenericDao<Customer> {

    public CustomerDaoImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void insert(Customer customer) {
        executeInsideTransaction(entityManager -> entityManager.persist(customer));
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = new Customer();
        try{
            customer = entityManager.find(Customer.class, id);
        } finally {
            entityManager.close();
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        executeInsideTransaction(entityManager -> entityManager.merge(customer));
    }

    @Override
    public void removeById(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getById(id)));
    }
}
