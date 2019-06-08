package main.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "beverage", schema = "dbo", catalog = "wholesale")
public class BeverageEntity {
    private int id;
    private Integer quantity;
    private Double price;
    private BeverageNameEntity beverageNameByBeverageNameId;
    private ManufacturerEntity manufacturerByManufacturerId;
    private ProviderEntity providerByProviderId;
    private BeverageTypeEntity beverageTypeByBeverageTypeId;
    private GasTypeEntity gasTypeByGasTypeId;
    private FlavorEntity flavorByFlavorId;
    private PackageEntity packageByPackageId;
    private Collection<OrderDetailsEntity> orderDetailsById;

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
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
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
        BeverageEntity that = (BeverageEntity) o;
        return id == that.id &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price);
    }

    @ManyToOne
    @JoinColumn(name = "beverageNameId", referencedColumnName = "id", nullable = false)
    public BeverageNameEntity getBeverageNameByBeverageNameId() {
        return beverageNameByBeverageNameId;
    }

    public void setBeverageNameByBeverageNameId(BeverageNameEntity beverageNameByBeverageNameId) {
        this.beverageNameByBeverageNameId = beverageNameByBeverageNameId;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturerId", referencedColumnName = "id", nullable = false)
    public ManufacturerEntity getManufacturerByManufacturerId() {
        return manufacturerByManufacturerId;
    }

    public void setManufacturerByManufacturerId(ManufacturerEntity manufacturerByManufacturerId) {
        this.manufacturerByManufacturerId = manufacturerByManufacturerId;
    }

    @ManyToOne
    @JoinColumn(name = "providerId", referencedColumnName = "id")
    public ProviderEntity getProviderByProviderId() {
        return providerByProviderId;
    }

    public void setProviderByProviderId(ProviderEntity providerByProviderId) {
        this.providerByProviderId = providerByProviderId;
    }

    @ManyToOne
    @JoinColumn(name = "beverageTypeId", referencedColumnName = "id", nullable = false)
    public BeverageTypeEntity getBeverageTypeByBeverageTypeId() {
        return beverageTypeByBeverageTypeId;
    }

    public void setBeverageTypeByBeverageTypeId(BeverageTypeEntity beverageTypeByBeverageTypeId) {
        this.beverageTypeByBeverageTypeId = beverageTypeByBeverageTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "gasTypeId", referencedColumnName = "id")
    public GasTypeEntity getGasTypeByGasTypeId() {
        return gasTypeByGasTypeId;
    }

    public void setGasTypeByGasTypeId(GasTypeEntity gasTypeByGasTypeId) {
        this.gasTypeByGasTypeId = gasTypeByGasTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "flavorId", referencedColumnName = "id")
    public FlavorEntity getFlavorByFlavorId() {
        return flavorByFlavorId;
    }

    public void setFlavorByFlavorId(FlavorEntity flavorByFlavorId) {
        this.flavorByFlavorId = flavorByFlavorId;
    }

    @ManyToOne
    @JoinColumn(name = "packageId", referencedColumnName = "id", nullable = false)
    public PackageEntity getPackageByPackageId() {
        return packageByPackageId;
    }

    public void setPackageByPackageId(PackageEntity packageByPackageId) {
        this.packageByPackageId = packageByPackageId;
    }

    @OneToMany(mappedBy = "beverageByBeverageId")
    public Collection<OrderDetailsEntity> getOrderDetailsById() {
        return orderDetailsById;
    }

    public void setOrderDetailsById(Collection<OrderDetailsEntity> orderDetailsById) {
        this.orderDetailsById = orderDetailsById;
    }

    @Override
    public String toString() {
        return "id: " + id + " " + beverageNameByBeverageNameId.getName();
    }
}
