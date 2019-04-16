package com.gmail.chernii.oleksii.entity;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Space on 16.04.2019.
 */
@Data
@Log4j
@Entity
@Table(name = "projects")
public class Project extends Model {
    @Column
    private String type;

    @Column
    private Double cost;

    @Column
    private Date date;

    @ManyToMany
    @JoinTable(name = "project_developer",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"))
    private Set<Developer> developers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "project_customer",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "project_company",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"))
    private Set<Company> companies = new HashSet<>();


    @PrePersist
    public void prePresist() {
        log.info("Project prePresist");
    }

    @PreUpdate
    public void preUpdate() {
        log.info("Project preUpdate");
    }

    @PreRemove
    public void preRemove() {
        log.info("Project preRemove");
    }

    @PostLoad
    public void postLoad() {
        log.info("Project postLoad");
    }

    @PostPersist
    public void postPersist() {
        log.info("Project postUpate");
    }

    @PostUpdate
    public void postUpdate() {
        log.info("Project postUpdate");
    }

    @PostRemove
    public void postRemove() {
        log.info("Project postRemove");
    }
}
