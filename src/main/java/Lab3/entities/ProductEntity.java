package Lab3.entities;

import Lab3.pojos.Editable;
import Lab3.pojos.SaleableItem;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product_table")
public abstract class ProductEntity extends Editable implements Serializable, SaleableItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductId());
    }

    public ProductEntity() {
        setProductId(String.valueOf((UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)/1000000000));
    }
//
//    public ProductEntity(String productId) {
//        this.productId = productId;
//    }

    @Column(name = "product_id")
    private String productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}