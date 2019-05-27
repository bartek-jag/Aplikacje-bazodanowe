package main.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "package", schema = "dbo", catalog = "wholesale")
public class PackageEntity {
    private int id;
    private String packageType;
    private BigDecimal capacity;
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
    @Column(name = "packageType", nullable = false, length = 20)
    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    @Basic
    @Column(name = "capacity", nullable = false, precision = 3)
    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageEntity that = (PackageEntity) o;
        return id == that.id &&
                Objects.equals(packageType, that.packageType) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageType, capacity);
    }

    @OneToMany(mappedBy = "packageByPackageId")
    public Collection<BeverageEntity> getBeveragesById() {
        return beveragesById;
    }

    public void setBeveragesById(Collection<BeverageEntity> beveragesById) {
        this.beveragesById = beveragesById;
    }

    @Override
    public String toString() {
        return "PackageEntity{" +
                "id=" + id +
                ", packageType='" + packageType + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
