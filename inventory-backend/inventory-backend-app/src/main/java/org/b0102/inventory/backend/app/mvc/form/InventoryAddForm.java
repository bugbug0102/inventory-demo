package org.b0102.inventory.backend.app.mvc.form;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class InventoryAddForm
{
    @NotNull
    private String name;

    @NotNull
    private String categoryName;

    @NotNull
    private String subCategoryName;

    @NotNull
    private BigInteger quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}
