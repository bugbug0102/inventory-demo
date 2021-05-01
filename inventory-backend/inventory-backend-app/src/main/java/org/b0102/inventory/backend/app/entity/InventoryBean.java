package org.b0102.inventory.backend.app.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="INVENTORY")
public class InventoryBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "INVENTORY_ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long inventoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY", nullable = false)
    private BigInteger quantity;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "UPDATED_DATE", nullable = false)
    private Date updatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SUB_CATEGORY_ID", nullable = false)
    private SubCategoryBean subCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public SubCategoryBean getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategoryBean subCategory) {
        this.subCategory = subCategory;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryBean that = (InventoryBean) o;
        return inventoryId.equals(that.inventoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId);
    }
}
