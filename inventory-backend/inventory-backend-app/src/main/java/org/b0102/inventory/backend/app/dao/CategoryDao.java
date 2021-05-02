package org.b0102.inventory.backend.app.dao;

import org.b0102.inventory.backend.app.entity.CategoryBean;

import java.util.List;
import java.util.Optional;

public interface CategoryDao
{
    int count();
    List<CategoryBean> findAll();
    Optional<CategoryBean> getByName(final String name);
    void add(final CategoryBean category);
}
