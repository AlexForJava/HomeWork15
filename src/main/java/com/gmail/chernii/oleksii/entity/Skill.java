package com.gmail.chernii.oleksii.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Space on 16.04.2019.
 */
@Data
@Log4j
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String level;

    @Column
    private String branch;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "skills")
    private Set<Developer> developers = new HashSet<>();

    @PrePersist
    public void prePresist() {
        log.info("Skill prePresist");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Skill preUpdate");
    }

    @PreRemove
    public void preRemove() {
        log.info("Skill preRemove");
    }

    @PostLoad
    public void postLoad() {
        log.info("Skill postLoad");
    }

    @PostPersist
    public void postPersist() {
        log.info("Skill postUpate");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Skill postUpdate");
    }

    @PostRemove
    public void postRemove() {
        log.info("Skill postRemove");
    }
}
