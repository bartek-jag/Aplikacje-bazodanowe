package main.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "gasType", schema = "dbo", catalog = "wholesale")
public class GasTypeEntity {
    private int id;
    private String gasType;
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
    @Column(name = "gasType", nullable = false, length = 20)
    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasTypeEntity that = (GasTypeEntity) o;
        return id == that.id &&
                Objects.equals(gasType, that.gasType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gasType);
    }

    @OneToMany(mappedBy = "gasTypeByGasTypeId")
    public Collection<BeverageEntity> getBeveragesById() {
        return beveragesById;
    }

    public void setBeveragesById(Collection<BeverageEntity> beveragesById) {
        this.beveragesById = beveragesById;
    }

    @Override
    public String toString() {
        return "GasTypeEntity{" +
                "id=" + id +
                ", gasType='" + gasType + '\'' +
                '}';
    }
}
