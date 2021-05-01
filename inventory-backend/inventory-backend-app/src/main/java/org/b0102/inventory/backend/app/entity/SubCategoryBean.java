package org.b0102.inventory.backend.app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="SUB_CATEGORY")
public class SubCategoryBean implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "SUB_CATEGORY_ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long subCategoryId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CATEGORY_ID", nullable = false)
    private CategoryBean category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubCategoryBean that = (SubCategoryBean) o;
        return subCategoryId.equals(that.subCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategoryId);
    }
}
