package com.gmail.chernii.oleksii.dao;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

/**
 * Created by Space on 18.04.2019.
 */
@Log4j
@AllArgsConstructor
public class EntityManagerHolder {
    protected EntityManager entityManager;

    protected void executeInsideTransaction(Consumer<EntityManager> action) {
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
