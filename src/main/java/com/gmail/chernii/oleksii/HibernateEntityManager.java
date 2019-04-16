package com.gmail.chernii.oleksii;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Space on 16.04.2019.
 */
public class HibernateEntityManager {
    private static final String PERSISTANCE_UNIT_NAME = "hibernate";
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void shutdown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
