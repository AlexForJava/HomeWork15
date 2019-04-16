package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class SkillDAO implements DAO<Skill> {
    private EntityManager entityManager;


    @Override
    public void insert(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(skill);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.error(e.getMessage());
        }
    }

    @Override
    public Skill get(Long id) {
        return entityManager.find(Skill.class, id);
    }

    @Override
    public void update(Skill skill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(skill);
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
