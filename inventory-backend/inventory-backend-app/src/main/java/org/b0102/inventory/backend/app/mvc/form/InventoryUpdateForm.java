package org.b0102.inventory.backend.app.mvc.form;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class InventoryUpdateForm
{
    @NotNull
    private Long inventoryId;

    @NotNull
    private BigInteger quantity;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }
}
