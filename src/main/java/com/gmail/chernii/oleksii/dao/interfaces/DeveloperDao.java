package com.gmail.chernii.oleksii.dao.interfaces;

import com.gmail.chernii.oleksii.entity.Developer;

import java.util.List;

/**
 * Created by Space on 17.04.2019.
 */
public interface DeveloperDao extends GenericDao<Developer> {
    List<Developer> getAll();
}
