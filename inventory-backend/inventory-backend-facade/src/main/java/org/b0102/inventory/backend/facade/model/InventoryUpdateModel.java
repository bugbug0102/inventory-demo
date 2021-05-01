package org.b0102.inventory.backend.facade.model;

import java.io.Serializable;
import java.math.BigInteger;

public class InventoryUpdateModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Long inventoryId;
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
