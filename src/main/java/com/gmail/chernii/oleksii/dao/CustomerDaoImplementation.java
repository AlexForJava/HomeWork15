package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.Dao;
import com.gmail.chernii.oleksii.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class CustomerDaoImplementation implements Dao<Customer> {
    private EntityManager entityManager;

    @Override
    public void insert(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
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
    public Customer get(Long id) {
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
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }finally {
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
        }finally {
            entityManager.close();
        }
    }
}
