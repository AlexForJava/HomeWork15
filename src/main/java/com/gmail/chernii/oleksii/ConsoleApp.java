package com.gmail.chernii.oleksii;

import com.gmail.chernii.oleksii.dao.DAO;
import com.gmail.chernii.oleksii.dao.DeveloperDAO;
import com.gmail.chernii.oleksii.entity.Developer;
import com.gmail.chernii.oleksii.entity.Sex;

import javax.persistence.EntityManager;

/**
 * Created by Space on 15.04.2019.
 */
public class ConsoleApp {
    public static void main(String[] args) {
        EntityManager entityManager = HibernateEntityManager.getEntityManager();

        Developer developer = new Developer();
        developer.setName("Alex");
        developer.setSex(Sex.MALE);
        developer.setAge(20);
        developer.setSalary(2000);

        DAO<Developer> developerDAO = new DeveloperDAO(entityManager);
        developerDAO.insert(developer);

        HibernateEntityManager.shutdown();

    }
}
