package org.b0102.inventory.backend.app.mvc.form;

import org.b0102.inventory.backend.facade.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InventoryAddFormValidator implements Validator
{
    @Autowired
    private InventoryService inventoryService;

    @Override
    public boolean supports(final Class<?> clazz)
    {
        return true;
    }

    @Override
    public void validate(final Object o, final Errors errors)
    {
        if(o instanceof InventoryAddForm)
        {
            final InventoryAddForm iaf = (InventoryAddForm) o;
            if(!inventoryService.validateCategoryByCategoryNameAndSubCategoryName(iaf.getCategoryName(), iaf.getSubCategoryName()))
            {
                errors.reject("invalid.category.and.sub.category");
            }
        }
    }
}
