package Math.Project4.models;

import jakarta.persistence.*;
import java.time.LocalDate;

import Math.Project4.models.SupplierModel;


@Entity(name= "products")
@Table(name= "products")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long proId;
    private String proName;
    private String proDescription;
    private Double proPrice;
    private LocalDate proDueDate;
    private Integer stkQuantity;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplierModel;
    @ManyToOne
    @JoinColumn(name = "products")
    private StockModel stockModel;

    // Getter e Setter
    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public Double getProPrice() {
        return proPrice;
    }

    public void setProPrice(Double proPrice) {
        this.proPrice = proPrice;
    }

    public LocalDate getProDueDate() {
        return proDueDate;
    }

    public void setProDueDate(LocalDate proDueDate) {
        this.proDueDate = proDueDate;
    }

    public Integer getStkQuantity() {
        return stkQuantity;
    }

    public void setStkQuantity(Integer stkQuantity) {
        this.stkQuantity = stkQuantity;
    }

    public SupplierModel getSupplierModel() {
        return supplierModel;
    }

    public void setSupplierModel(SupplierModel supplierModel) {
        this.supplierModel = supplierModel;
    }

    public StockModel getStockModel() {
        return stockModel;
    }

    public void setStockModel(StockModel stockModel) {
        this.stockModel = stockModel;
    }
}
