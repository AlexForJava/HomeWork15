package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.ProjectDao;
import com.gmail.chernii.oleksii.entity.Project;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class ProjectDaoImplementation implements ProjectDao {
    private EntityManager entityManager;

    @Override
    public void insert(Project project) {
        executeInsideTransaction(entityManager -> entityManager.persist(project));
    }

    @Override
    public Project get(Long id) {
        Project project = new Project();
        try {
            project = entityManager.find(Project.class, id);
        } finally {
            entityManager.close();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        executeInsideTransaction(entityManager -> entityManager.merge(project));
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
