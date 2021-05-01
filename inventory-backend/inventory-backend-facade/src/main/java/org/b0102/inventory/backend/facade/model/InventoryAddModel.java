package org.b0102.inventory.backend.facade.model;

import java.io.Serializable;
import java.math.BigInteger;

public class InventoryAddModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;
    private BigInteger quantity;
    private String categoryName;
    private String subCategoryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }
}
