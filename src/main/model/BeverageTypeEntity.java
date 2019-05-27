package main.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "beverageType", schema = "dbo", catalog = "wholesale")
public class BeverageTypeEntity {
    private int id;
    private String beverageType;
    private BigDecimal tax;
    private Collection<BeverageEntity> beveragesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "tax", nullable = false, precision = 2)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeverageTypeEntity that = (BeverageTypeEntity) o;
        return id == that.id &&
                Objects.equals(beverageType, that.beverageType) &&
                Objects.equals(tax, that.tax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beverageType, tax);
    }

    @OneToMany(mappedBy = "beverageTypeByBeverageTypeId")
    public Collection<BeverageEntity> getBeveragesById() {
        return beveragesById;
    }

    public void setBeveragesById(Collection<BeverageEntity> beveragesById) {
        this.beveragesById = beveragesById;
    }

    @Override
    public String toString() {
        return "BeverageTypeEntity{" +
                "id=" + id +
                ", beverageType='" + beverageType + '\'' +
                ", tax=" + tax +
                '}';
    }
}
