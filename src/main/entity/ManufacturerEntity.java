package main.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "manufacturer", schema = "dbo", catalog = "wholesale")
public class ManufacturerEntity {
    private int id;
    private String name;
    private Collection<BeverageEntity> beveragesById;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManufacturerEntity that = (ManufacturerEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "manufacturerByManufacturerId")
    public Collection<BeverageEntity> getBeveragesById() {
        return beveragesById;
    }

    public void setBeveragesById(Collection<BeverageEntity> beveragesById) {
        this.beveragesById = beveragesById;
    }

    @Override
    public String toString() {
        return "id: " + id + " " + name;
    }
}
