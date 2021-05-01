package org.b0102.inventory.backend.facade.model;

import java.io.Serializable;

public class CategoryForEnquiryModel implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
