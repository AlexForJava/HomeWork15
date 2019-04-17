package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.Dao;
import com.gmail.chernii.oleksii.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class CustomerDaoImplementation implements Dao<Customer> {
    private EntityManager entityManager;

    @Override
    public void insert(Customer customer) {
        executeInsideTransaction(entityManager -> entityManager.persist(customer));
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
        executeInsideTransaction(entityManager -> entityManager.merge(customer));
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
