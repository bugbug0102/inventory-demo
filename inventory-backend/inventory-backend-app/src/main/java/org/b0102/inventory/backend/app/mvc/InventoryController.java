package org.b0102.inventory.backend.app.mvc;

import org.b0102.inventory.backend.app.mvc.form.InventoryAddForm;
import org.b0102.inventory.backend.app.mvc.form.InventoryAddFormValidator;
import org.b0102.inventory.backend.app.mvc.form.InventoryUpdateForm;
import org.b0102.inventory.backend.facade.model.*;
import org.b0102.inventory.backend.facade.service.InventoryOperationException;
import org.b0102.inventory.backend.facade.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("inventory")
class InventoryController
{
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryAddFormValidator inventoryAddFormValidator;

    @InitBinder
    private void initBinder(final WebDataBinder binder)
    {
        binder.addValidators(inventoryAddFormValidator);
    }

    @PostMapping("update-inventory")
    void updateInventory(@RequestBody final InventoryUpdateForm inventoryUpdateForm)
    {
        final InventoryUpdateModel iu = new InventoryUpdateModel();
        iu.setInventoryId(inventoryUpdateForm.getInventoryId());
        iu.setQuantity(inventoryUpdateForm.getQuantity());
        inventoryService.updateInventoryByInventoryUpdate(iu);
    }

    @PostMapping("add-inventory")
    Map<String,?> addInventory(@Valid @RequestBody final InventoryAddForm inventoryAddForm, final BindingResult bindingResult) throws InventoryOperationException
    {
        final Map<String, List<ObjectError>> ret = new HashMap<>();
        if(!bindingResult.hasErrors())
        {
            final InventoryAddModel ia = new InventoryAddModel();
            ia.setName(inventoryAddForm.getName());
            ia.setCategoryName(inventoryAddForm.getCategoryName());
            ia.setSubCategoryName(inventoryAddForm.getSubCategoryName());
            ia.setQuantity(inventoryAddForm.getQuantity());
            inventoryService.addInventoryByInventoryAdd(ia);

        }else
        {
            ret.put("error", bindingResult.getAllErrors());
        }
        return ret;
    }

    @GetMapping("find-inventory-for-enquiry-all")
    List<InventoryForEnquiryModel> findInventoryForEnquiryAll()
    {
        return inventoryService.findInventoryForEnquiryAll();
    }

    @GetMapping("get-inventory-for-enquiry-by-inventory-id")
    Optional<InventoryForEnquiryModel> getInventoryForEnquiryByInventoryId(@RequestParam final Long inventoryId)
    {
        return inventoryService.getInventoryForEnquiryByInventoryId(inventoryId);
    }

    @GetMapping("find-category-for-enquiry-all")
    List<CategoryForEnquiryModel> findCategoryForEnquiryAll()
    {
        return inventoryService.findCategoryForEnquiryAll();
    }

    @GetMapping("find-sub-category-for-enquiry-all")
    List<SubCategoryForEnquiryModel> findSubCategoryForEnquiryAll()
    {
        return inventoryService.findSubCategoryForEnquiryAll();
    }
}
