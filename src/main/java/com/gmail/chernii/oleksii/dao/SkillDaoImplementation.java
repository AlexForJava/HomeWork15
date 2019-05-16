package com.gmail.chernii.oleksii.dao;

import com.gmail.chernii.oleksii.dao.interfaces.SkillDao;
import com.gmail.chernii.oleksii.entity.Skill;
import lombok.extern.log4j.Log4j;

import javax.persistence.EntityManager;

/**
 * Created by Space on 16.04.2019.
 */
@Log4j
public class SkillDaoImplementation extends EntityManagerHolder implements SkillDao {

    public SkillDaoImplementation(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void insert(Skill skill) {
        executeInsideTransaction(entityManager -> entityManager.persist(skill));
    }

    @Override
    public Skill getById(Long id) {
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
    public void removeById(Long id) {
        executeInsideTransaction(entityManager -> entityManager.remove(getById(id)));
    }
}
