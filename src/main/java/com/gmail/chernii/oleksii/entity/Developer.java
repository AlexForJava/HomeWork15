package com.gmail.chernii.oleksii.entity;


import lombok.Data;
import lombok.extern.log4j.Log4j;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Space on 15.04.2019.
 */
@Data
@Log4j
@Entity
@Table(name = "developers")
@NamedQuery(name ="Developer.getAll", query = "SELECT d FROM Developer d")
public class Developer extends Model {
    @Column
    private Integer age;

    @Column
    private Sex sex;

    @Column
    private Integer salary;

    @ManyToMany
    @JoinTable(name = "developer_skill",
            joinColumns = @JoinColumn(name = "developer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"))
    private Set<Skill> skills = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "developers")
    private Set<Project> projects = new HashSet<>();

}
