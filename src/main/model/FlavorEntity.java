package main.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "flavor", schema = "dbo", catalog = "wholesale")
public class FlavorEntity {
    private int id;
    private String flavor;
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
    @Column(name = "flavor", nullable = false, length = 50)
    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlavorEntity that = (FlavorEntity) o;
        return id == that.id &&
                Objects.equals(flavor, that.flavor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flavor);
    }

    @OneToMany(mappedBy = "flavorByFlavorId")
    public Collection<BeverageEntity> getBeveragesById() {
        return beveragesById;
    }

    public void setBeveragesById(Collection<BeverageEntity> beveragesById) {
        this.beveragesById = beveragesById;
    }

    @Override
    public String toString() {
        return "FlavorEntity{" +
                "id=" + id +
                ", flavor='" + flavor + '\'' +
                '}';
    }
}
