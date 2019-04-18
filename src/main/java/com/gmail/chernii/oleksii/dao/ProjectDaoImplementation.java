package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.ProjectDao;
import com.gmail.chernii.oleksii.entity.Project;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
public class ProjectDaoImplementation extends EntityManagerHolder implements ProjectDao {

    public ProjectDaoImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void insert(Project project) {
        executeInsideTransaction(entityManager -> entityManager.persist(project));
    }

    @Override
    public Project getById(Long id) {
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
    public void removeById(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getById(id)));
    }
}
