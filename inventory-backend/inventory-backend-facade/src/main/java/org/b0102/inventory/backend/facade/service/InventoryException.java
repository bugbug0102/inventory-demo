package org.b0102.inventory.backend.facade.service;

public class InventoryException extends Exception
{
    private static final long serialVersionUID = 1L;

    public InventoryException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

    public InventoryException(final String message)
    {
        super(message);
    }
}
