package Math.Project4.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name= "movStock")
@Table(name= "movStock")
public class MovStockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;
    private String movType;
    private Integer quantity;
    private LocalDate dateMov;

    //Getters e Setters
    public Long getMovId() {
        return movId;
    }

    public void setMovId(Long movId) {
        this.movId = movId;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public String getMovType() {
        return movType;
    }

    public void setMovType(String movType) {
        this.movType = movType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateMov() {
        return dateMov;
    }

    public void setDateMov(LocalDate dateMov) {
        this.dateMov = dateMov;
    }
}
