package Math.Project4.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name= "stock")
@Table(name= "stock")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stkId;
    @Column(unique = true)
    private String stkName;
    @OneToMany(mappedBy = "stockModel")
    private List<ProductModel> products;


    //Getters e Setter
    public Long getStkId() {
        return stkId;
    }

    public void setStkId(Long stkId) {
        this.stkId = stkId;
    }

    public String getStkName() {
        return stkName;
    }

    public void setStkName(String stkName) {
        this.stkName = stkName;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

}
