package org.b0102.inventory.backend.app.dao;

import org.b0102.inventory.backend.app.entity.SubCategoryBean;

import java.util.List;
import java.util.Optional;

public interface SubCategoryDao
{
    List<SubCategoryBean> findAll();
    Optional<SubCategoryBean> getByName(final String name);
    void add(final SubCategoryBean subCategory);
    boolean existsByNameAndCategoryName(final String name, final String categoryName);

}
