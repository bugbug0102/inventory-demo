package org.b0102.inventory.backend.facade.service;

public class InventoryOperationException extends InventoryException
{
    public InventoryOperationException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public InventoryOperationException(final String message)
    {
        super(message);
    }

}
