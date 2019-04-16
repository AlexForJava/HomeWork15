package com.gmail.chernii.oleksii.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "customers")
public class Customer extends Model {
    @Column
    private String country;

   @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "customers")
    private Set<Project> projects = new HashSet<>();

    @PrePersist
    public void prePresist() {
        log.info("Customer prePresist");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Customer preUpdate");
    }

    @PreRemove
    public void preRemove() {
        log.info("Customer preRemove");
    }

    @PostLoad
    public void postLoad() {
        log.info("Customer postLoad");
    }

    @PostPersist
    public void postPersist() {
        log.info("Customer postUpate");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Customer postUpdate");
    }

    @PostRemove
    public void postRemove() {
        log.info("Customer postRemove");
    }
}
