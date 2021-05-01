package org.b0102.inventory.backend.app.service.impl;

import org.b0102.inventory.backend.app.dao.CategoryDao;
import org.b0102.inventory.backend.app.dao.InventoryDao;
import org.b0102.inventory.backend.app.dao.SubCategoryDao;
import org.b0102.inventory.backend.app.entity.CategoryBean;
import org.b0102.inventory.backend.app.entity.InventoryBean;
import org.b0102.inventory.backend.app.entity.SubCategoryBean;
import org.b0102.inventory.backend.facade.model.*;
import org.b0102.inventory.backend.facade.service.InventoryOperationException;
import org.b0102.inventory.backend.facade.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class InventoryServiceImpl implements InventoryService
{
    private final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Autowired
    private SubCategoryDao subCategoryDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryForEnquiryModel> findInventoryForEnquiryAll() {
        return inventoryDao.findAll().stream().map(i -> {
            final InventoryForEnquiryModel ie = new InventoryForEnquiryModel();
            ie.setInventoryId(i.getInventoryId());
            ie.setName(i.getName());
            ie.setCategoryName(i.getSubCategory().getCategory().getName());
            ie.setSubCategoryName(i.getSubCategory().getName());
            ie.setQuantity(i.getQuantity());
            return ie;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<InventoryForEnquiryModel> getInventoryForEnquiryByInventoryId(final Long inventoryId)
    {
        return inventoryDao.getById(inventoryId).map( i -> {
            final InventoryForEnquiryModel ie = new InventoryForEnquiryModel();
            ie.setInventoryId(i.getInventoryId());
            ie.setName(i.getName());
            ie.setCategoryName(i.getSubCategory().getCategory().getName());
            ie.setSubCategoryName(i.getSubCategory().getName());
            ie.setQuantity(i.getQuantity());
            return ie;
        });
    }

    @Transactional
    @Override
    public boolean updateInventoryByInventoryUpdate(final InventoryUpdateModel inventoryUpdate)
    {
        final Optional<InventoryBean> io = inventoryDao.getById(inventoryUpdate.getInventoryId());
        if(io.isPresent())
        {
            final InventoryBean i = io.get();
            final Date now = new Date();
            i.setQuantity(inventoryUpdate.getQuantity());
            i.setUpdatedDate(now);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void addInventoryByInventoryAdd(final InventoryAddModel inventoryAdd) throws InventoryOperationException
    {
        if(validateCategoryByCategoryNameAndSubCategoryName(inventoryAdd.getCategoryName(), inventoryAdd.getSubCategoryName()))
        {
            final Date now = new Date();
            final InventoryBean i = new InventoryBean();
            i.setName(inventoryAdd.getName());
            i.setSubCategory(subCategoryDao.getByName(inventoryAdd.getSubCategoryName()).get());
            i.setQuantity(inventoryAdd.getQuantity());
            i.setUpdatedDate(now);
            i.setCreatedDate(now);
            inventoryDao.add(i);

        }else
        {
            throw new InventoryOperationException("invalid.category.and.sub.category");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<SubCategoryForEnquiryModel> findSubCategoryForEnquiryAll() {
        return subCategoryDao.findAll().stream().map(sc -> {
            final SubCategoryForEnquiryModel scfe = new SubCategoryForEnquiryModel();
            scfe.setName(sc.getName());
            return scfe;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryForEnquiryModel> findCategoryForEnquiryAll() {
        return categoryDao.findAll().stream().map(c -> {
            final CategoryForEnquiryModel cfe = new CategoryForEnquiryModel();
            cfe.setName(c.getName());
            return cfe;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public boolean validateCategoryByCategoryNameAndSubCategoryName(final String categoryName, final String subCategoryName)
    {
        return subCategoryDao.existsByNameAndCategoryName(subCategoryName, categoryName);
    }

    @PostConstruct
    private void initialize()
    {
        /* for putting default category and sub-category under H2 database */
        final TransactionTemplate tt = new TransactionTemplate(platformTransactionManager);
        tt.execute( action -> {
            if(categoryDao.count() <= 0)
            {
                final class Pair<X,Y>
                {
                    final X x;
                    final Y y;
                    Pair(final X x, final Y y)
                    {
                        this.x = x;
                        this.y = y;
                    }
                }

                final List<Pair<String,String>> cats = new ArrayList<>();
                cats.add(new Pair<String,String>("Food", "Apple"));
                cats.add(new Pair<String,String>("Food", "Orange"));
                cats.add(new Pair<String,String>("Food", "Meat"));
                cats.add(new Pair<String,String>("Clothes", "Shirt"));
                cats.add(new Pair<String,String>("Clothes", "Socks"));
                cats.add(new Pair<String,String>("Clothes", "Trousers"));
                cats.add(new Pair<String,String>("Phone", "iPhone"));
                cats.add(new Pair<String,String>("Phone", "Android"));
                cats.add(new Pair<String,String>("Stationary", "Pencil"));
                cats.add(new Pair<String,String>("Stationary", "Ruler"));
                cats.add(new Pair<String,String>("Stationary", "Eraser"));
                cats.add(new Pair<String,String>("Furniture", "Chair"));
                cats.add(new Pair<String,String>("Furniture", "Desk"));
                cats.add(new Pair<String,String>("Furniture", "Bed"));
                cats.add(new Pair<String,String>("Animal", "Fish"));
                cats.add(new Pair<String,String>("Animal", "Dog"));
                cats.add(new Pair<String,String>("Animal", "Hamster"));

                cats.stream().map(c -> c.x).distinct().map( c -> {
                    final CategoryBean nc = new CategoryBean();
                    nc.setName(c);
                    return nc;
                }).forEach(c -> {
                    categoryDao.add(c);
                });

                cats.stream().map( p -> {
                    final SubCategoryBean nsc = new SubCategoryBean();
                    nsc.setCategory(categoryDao.getByName(p.x).get());
                    nsc.setName(p.y);
                    return nsc;
                }).forEach(sc -> {
                    subCategoryDao.add(sc);
                });
            }
            return 0;
        });




    }
}
