package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.SkillDao;
import com.gmail.chernii.oleksii.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
@AllArgsConstructor
public class SkillDaoImplementation implements SkillDao {
    private EntityManager entityManager;


    @Override
    public void insert(Skill skill) {
        executeInsideTransaction(entityManager -> entityManager.persist(skill));
    }

    @Override
    public Skill get(Long id) {
        Skill skill = new Skill();
        try {
            skill = entityManager.find(Skill.class, id);
        } finally {
            entityManager.close();
        }
        return skill;
    }

    @Override
    public void update(Skill skill) {
        executeInsideTransaction(entityManager -> entityManager.merge(skill));
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
