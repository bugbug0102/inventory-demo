package org.b0102.inventory.backend.app.dao;

import org.b0102.inventory.backend.app.entity.InventoryBean;

import java.util.List;
import java.util.Optional;

public interface InventoryDao
{
    List<InventoryBean> findAll();
    Optional<InventoryBean> getById(final Long id);
    void add(final InventoryBean inventoryBean);
}
