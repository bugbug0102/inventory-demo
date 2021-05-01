package org.b0102.inventory.backend.facade.service;

import org.b0102.inventory.backend.facade.model.*;

import java.util.List;
import java.util.Optional;

public interface InventoryService
{
    List<InventoryForEnquiryModel> findInventoryForEnquiryAll();
    Optional<InventoryForEnquiryModel> getInventoryForEnquiryByInventoryId(final Long inventoryId);
    boolean updateInventoryByInventoryUpdate(final InventoryUpdateModel inventoryUpdate);
    void addInventoryByInventoryAdd(final InventoryAddModel inventoryAdd) throws InventoryOperationException;

    List<SubCategoryForEnquiryModel> findSubCategoryForEnquiryAll();
    List<CategoryForEnquiryModel> findCategoryForEnquiryAll();
    boolean validateCategoryByCategoryNameAndSubCategoryName(final String categoryName, final String subCategoryName);

}
