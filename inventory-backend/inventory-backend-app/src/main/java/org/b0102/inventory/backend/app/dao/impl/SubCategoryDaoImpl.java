package org.b0102.inventory.backend.app.dao.impl;

import org.b0102.inventory.backend.app.dao.SubCategoryDao;
import org.b0102.inventory.backend.app.entity.SubCategoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
class SubCategoryDaoImpl implements SubCategoryDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SubCategoryBean> findAll()
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<SubCategoryBean> cq = cb.createQuery(SubCategoryBean.class);
        cq.from(SubCategoryBean.class);
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public void add(final SubCategoryBean subCategory)
    {
        entityManager.persist(subCategory);
    }

    @Override
    public Optional<SubCategoryBean> getByName(final String name)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<SubCategoryBean> cq = cb.createQuery(SubCategoryBean.class);
        final Root<SubCategoryBean> r = cq.from(SubCategoryBean.class);
        cq.where(cb.equal(r.get("name"), name));
        try
        {
            return Optional.ofNullable(entityManager.createQuery(cq).getSingleResult());
        }catch(final NoResultException ex)
        {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByNameAndCategoryName(final String name, final String categoryName)
    {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<SubCategoryBean> r = cq.from(SubCategoryBean.class);
        final Path<?> p = r.get("category");
        cq.select(cb.count(r)).where(cb.and(cb.equal(p.get("name"), categoryName), cb.equal(r.get("name"), name)));
        return entityManager.createQuery(cq).getSingleResult().intValue() > 0;

    }
}
