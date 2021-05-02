package org.b0102.inventory.backend.app.dao.impl;


import org.b0102.inventory.backend.app.dao.CategoryDao;
import org.b0102.inventory.backend.app.entity.CategoryBean;
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
class CategoryDaoImpl implements CategoryDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int count()
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(CategoryBean.class)));
        return entityManager.createQuery(cq).getSingleResult().intValue();
    }

    @Override
    public List<CategoryBean> findAll()
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<CategoryBean> cq = cb.createQuery(CategoryBean.class);
        cq.from(CategoryBean.class);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void add(final CategoryBean category)
    {
        entityManager.persist(category);
    }

    @Override
    public Optional<CategoryBean> getByName(final String name)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<CategoryBean> cq = cb.createQuery(CategoryBean.class);
        final Root<CategoryBean> r = cq.from(CategoryBean.class);
        cq.where(cb.equal(r.get("name"), name));
        try
        {
            return Optional.of(entityManager.createQuery(cq).getSingleResult());
        }catch(final NoResultException ex)
        {
            return Optional.empty();
        }
    }
}
