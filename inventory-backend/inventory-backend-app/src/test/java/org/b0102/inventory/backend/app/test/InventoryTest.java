package org.b0102.inventory.backend.app.test;

import org.b0102.inventory.backend.app.InventoryApplication;
import org.b0102.inventory.backend.facade.model.InventoryAddModel;
import org.b0102.inventory.backend.facade.service.InventoryOperationException;
import org.b0102.inventory.backend.facade.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

@SpringBootTest(classes = InventoryApplication.class)
public class InventoryTest
{
    @Autowired
    private InventoryService inventoryService;

    @Test
    void testInventoryAdd() throws InventoryOperationException
    {
        final InventoryAddModel ia = new InventoryAddModel();
        ia.setName("TEST-001");
        ia.setQuantity(BigInteger.ONE);
        ia.setCategoryName("Food"); /** Assumed category installed **/
        ia.setSubCategoryName("Meat"); /** Assumed sub-category installed **/
        inventoryService.addInventoryByInventoryAdd(ia);

        assertTrue(inventoryService.findInventoryForEnquiryAll().stream().anyMatch( f -> "TEST-001".equals(f.getName())));
    }

    @Test
    void testInventoryAddNegative() throws InventoryOperationException
    {
        boolean success = false;
        try
        {
            final InventoryAddModel ia = new InventoryAddModel();
            ia.setName("TEST-002");
            ia.setQuantity(BigInteger.ONE.negate());
            ia.setCategoryName("Food"); /** Assumed category installed **/
            ia.setSubCategoryName("Meat"); /** Assumed sub-category installed **/
            inventoryService.addInventoryByInventoryAdd(ia);
            success = true;
        }catch(final InventoryOperationException ex)
        {
            success = false;
        }
        assertFalse(success);
    }

    @Test
    void testInventoryAddZero() throws InventoryOperationException
    {
        boolean success = false;
        try
        {
            final InventoryAddModel ia = new InventoryAddModel();
            ia.setName("TEST-003");
            ia.setQuantity(BigInteger.ZERO);
            ia.setCategoryName("Food"); /** Assumed category installed **/
            ia.setSubCategoryName("Meat"); /** Assumed sub-category installed **/
            inventoryService.addInventoryByInventoryAdd(ia);
            success = true;
        }catch(final InventoryOperationException ex)
        {
            success = false;
        }
        assertFalse(success);
    }

    @Test
    void testInventoryAddInvalidCategory() throws InventoryOperationException
    {
        boolean success = false;
        try
        {
            final InventoryAddModel ia = new InventoryAddModel();
            ia.setName("TEST-003");
            ia.setQuantity(BigInteger.ONE);
            ia.setCategoryName("Phone"); /** Assumed category installed **/
            ia.setSubCategoryName("Meat"); /** Assumed sub-category installed **/
            inventoryService.addInventoryByInventoryAdd(ia);
            success = true;
        }catch(final InventoryOperationException ex)
        {
            success = false;
        }
        assertFalse(success);
    }


    @Test
    void testInventoryValidCategory() throws InventoryOperationException
    {
        assertTrue(inventoryService.validateCategoryByCategoryNameAndSubCategoryName("Phone", "Android"));
    }

    @Test
    void testInventoryInvalidCategory() throws InventoryOperationException
    {
        assertFalse(inventoryService.validateCategoryByCategoryNameAndSubCategoryName("Phone", "Meat"));
    }

    @Test
    void testInventoryNonExistedCategory() throws InventoryOperationException
    {
        assertFalse(inventoryService.validateCategoryByCategoryNameAndSubCategoryName("Building", "Android"));
        assertFalse(inventoryService.validateCategoryByCategoryNameAndSubCategoryName("Phone", "Samsung"));
    }
}
