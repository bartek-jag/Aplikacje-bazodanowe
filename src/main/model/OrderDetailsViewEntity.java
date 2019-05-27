package main.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderDetailsView", schema = "dbo", catalog = "wholesale")
public class OrderDetailsViewEntity {
    private int id;
    private String name;
    private String beverageType;
    private String flavor;
    private String gasType;
    private String packageType;
    private int quantity;
    private Double price;

    @Basic
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "beverageType", nullable = false, length = 20)
    public String getBeverageType() {
        return beverageType;
    }

    public void setBeverageType(String beverageType) {
        this.beverageType = beverageType;
    }

    @Basic
    @Column(name = "flavor", nullable = true, length = 50)
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Basic
    @Column(name = "gasType", nullable = true, length = 20)
    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    @Basic
    @Column(name = "packageType", nullable = true, length = 20)
    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Id
    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailsViewEntity that = (OrderDetailsViewEntity) o;
        return id == that.id &&
                quantity == that.quantity &&
                Objects.equals(name, that.name) &&
                Objects.equals(beverageType, that.beverageType) &&
                Objects.equals(flavor, that.flavor) &&
                Objects.equals(gasType, that.gasType) &&
                Objects.equals(packageType, that.packageType) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, beverageType, flavor, gasType, packageType, quantity, price);
    }
}
