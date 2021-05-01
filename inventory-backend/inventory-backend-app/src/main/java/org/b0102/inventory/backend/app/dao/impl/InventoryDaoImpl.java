package org.b0102.inventory.backend.app.dao.impl;

import org.b0102.inventory.backend.app.dao.InventoryDao;
import org.b0102.inventory.backend.app.entity.InventoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
class InventoryDaoImpl implements InventoryDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<InventoryBean> findAll()
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<InventoryBean> cq = cb.createQuery(InventoryBean.class);
        cq.from(InventoryBean.class);
        return entityManager.createQuery(cq).getResultList();
    };

    @Override
    public Optional<InventoryBean> getById(final Long id)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<InventoryBean> cq = cb.createQuery(InventoryBean.class);
        final Root<InventoryBean> r = cq.from(InventoryBean.class);
        cq.where(cb.equal(r.get("inventoryId"), id));
        try
        {
            return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
        }catch(final NoResultException ex)
        {
            return Optional.empty();
        }

    }

    @Override
    public void add(final InventoryBean inventoryBean)
    {
        entityManager.persist(inventoryBean);
    }
}
