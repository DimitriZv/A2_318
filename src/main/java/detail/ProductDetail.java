package detail;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class ProductDetail {

    private @Id @GeneratedValue Long id;
    private String description;
    private String comment;

    ProductDetail() {}

    public ProductDetail(String description, String comment) {
        this.description = description;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof ProductDetail))
            return false;
        ProductDetail productDetail = (ProductDetail) o;
        return Objects.equals(this.id, productDetail.id) && Objects.equals(this.description, productDetail.description)
                && Objects.equals(this.comment, productDetail.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description, this.comment);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}