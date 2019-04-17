package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.ProjectDao;
import com.gmail.chernii.oleksii.entity.Project;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class ProjectDaoImplementation implements ProjectDao {
    private EntityManager entityManager;

    @Override
    public void insert(Project project) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(project);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }
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
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(project);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
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
        }
    }
}
