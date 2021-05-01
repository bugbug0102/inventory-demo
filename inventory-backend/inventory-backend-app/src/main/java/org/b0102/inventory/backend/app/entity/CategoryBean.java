package org.b0102.inventory.backend.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="CATEGORY")
public class CategoryBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATEGORY_ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryBean that = (CategoryBean) o;
        return categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
